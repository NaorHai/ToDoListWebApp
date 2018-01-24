import com.todolist.configuration.CookieHelper;
import com.todolist.exception.toDoList.ToDoListException;
import com.todolist.pojo.Item;
import com.todolist.service.IToDoListService;
import com.todolist.service.IToDoListServiceImpl;
import com.todolist.service.UserService;
import com.todolist.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Haimov on 14/12/2017.
 * Application REST API controller
 */
@WebServlet("/todolist/*")
public class ToDoServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private UserService userService = new UserServiceImpl();
    private IToDoListService iToDoListService = new IToDoListServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        String path = request.getPathInfo();
        boolean isAuthenticated = false;


        switch (path) {
            // case if user did login
            case "/login":

                String userName = request.getParameter("username");
                String userPass = request.getParameter("password");
                String logink = request.getParameter("loginkeeping");

                try {
                    //keep user logged in
                    if (session.getAttribute("isAuthenticated") == "true") {
                        isAuthenticated = true;
                    }
                    if (isAuthenticated && userName == null) {
                        userName= CookieHelper.getValueCookie("username", request);
                    }
                     if (userName == null || userName.isEmpty()) {
                        response.sendRedirect("/ToDoListWebProject/index.jsp");
                        return;
                    }
//                    if (!hiber.isUserNameEmailExists(userName)) {
//                        // username do not exist in DB send in request attribute that indicate bad	insert username
//                        request.setAttribute("isBaduserName", "badUsername");
//                        getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
//                        return;
//                    }
                    if (!isAuthenticated) {
                        if (!userService.checkUserLogin(userName, userPass, response)) {
                            // password do not match send in request attribute that indicate bad password
                            String bedUsername = "badPassword";
                            request.setAttribute("userName", userName);
                            request.setAttribute("isBadPassword", bedUsername);
                            getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
                            return;
                        }
                    }

                    // user credentials are valid
                    isAuthenticated = true;
                    userService.login(session, userName,logink, request, response, true);

                    // get to do list of user and send it to todolist jsp file
                    List<Item> todolist = iToDoListService.getItemsByUserId(userName);

                    if (todolist == null || todolist.size() == 0) {
                        throw new ToDoListException("error with getting user's: " + userName + " to-do list");
                    }
                    request.setAttribute("list", todolist);

                    getServletContext().getRequestDispatcher("/todolist.jsp").include(request, response);
                    return;

                } catch (ToDoListException e) {

                    request.setAttribute("errorMessage", e);
                    getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);

                } catch (Exception e) {
                    ToDoListException ex = new ToDoListException(e.getMessage());
                    request.setAttribute("errorMessage", ex);
                    getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
                }

                break;
            // case if user did registration
            case "/register":
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                String userNamemail = request.getParameter("emailusername");
                String password = request.getParameter("passwordsignup");

                // check if this user do not exist in DB already
                try {
                    if (userNamemail != null && !userNamemail.isEmpty()) {
                        if (userService.isUserAlreadyExist(userNamemail)) {
                            request.setAttribute("isExistedBaduserName", "exsitedUserName");
                            request.setAttribute("firstName", firstName);
                            request.setAttribute("lastName", lastName);
                            getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
                            return;
                        } else {
                            // user not exist create a new user
                            // do authentication
                            isAuthenticated = true;
                            userService.registerUser(session,
                                    userNamemail, password, firstName, lastName, request, response );

                            getServletContext().getRequestDispatcher("/todolist.jsp").include(request, response);
                            return;
                        }
                    } else {
                        response.sendRedirect("/ToDoListWebProject/index.jsp");
                        return;
                    }
                } catch (Exception e) {
                    ToDoListException ex = new ToDoListException(e.getMessage());
                    request.setAttribute("errorMessage", ex);
                    getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
                }

                session.setAttribute("username", request.getParameter("username"));
                dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.include(request, response);
                break;

            // case if user use ToDo List and did some action
            case "/mylist":
                /** Check Is Authenticated **/
                if ((boolean) session.getAttribute("isAuthenticated") != true) {
                    // user do logout, do include to login
                    response.sendRedirect("/ToDoListWebProject/index.jsp");
                    return;
                }
                /** End Is Authenticated **/
                String action = request.getParameter("action");
                // take user name of user from session for add task to specific user
                userName = (String) session.getAttribute("username");

                // start check the action that user did
                // user add new task to ToDo list
                if ("Add".equalsIgnoreCase(action)) {
                    String taskName = request.getParameter("taskname");
                    String taskDescription = request.getParameter("taskdescription");
                    if (taskName != null && taskDescription != null) {
                        taskName = taskName.trim();
                        taskDescription = taskDescription.trim();
                        try {
                            iToDoListService.createItem(userName,taskName, taskDescription);
                            List<Item> todolist = iToDoListService.getItemsByUserId(userName);

                            request.setAttribute("list", todolist);
                            getServletContext().getRequestDispatcher("/todolist.jsp").include(request, response);
                            return;

                        } catch (Exception e) {
                            ToDoListException ex = new ToDoListException(e.getMessage());
                            request.setAttribute("errorMessage", ex);
                            getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
                        }

                    }
                    // user delete some task from ToDo list
                } else if ("delete".equalsIgnoreCase(action)) {

                    String taskid = request.getParameter("itemId");

                    try {
                        iToDoListService.deleteItemById(taskid);
                        List<Item> todolist = iToDoListService.getItemsByUserId(userName);
                        request.setAttribute("list", todolist);
                        getServletContext().getRequestDispatcher("/todolist.jsp").include(request, response);
                        return;

                    } catch (Exception e) {
                        ToDoListException ex = new ToDoListException(e.getMessage());
                        request.setAttribute("errorMessage", ex);
                        getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
                    }

                    // user mark some task to done
                } else if ("did".equalsIgnoreCase(action)) {

//                    String taskid = request.getParameter("itemId");
//                    try {
//                        iToDoListService.updateTaskdone(taskid);
//                        List<Item> todolist = iToDoListService.getItemsByUserId(userName);
//                        request.setAttribute("list", todolist);
//                        getServletContext().getRequestDispatcher("/todolist.jsp").include(request, response);
//                        return;
//
//                    } catch (ToDoListException e) {
//                        request.setAttribute("errorMessage", e);
//                        getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
//
//                    }catch (Exception e) {
//                        ToDoListException ex = new ToDoListException(e.getMessage());
//                        request.setAttribute("errorMessage", ex);
//                        getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
//                    }
                    //user edit some task
                } else if ("edit".equalsIgnoreCase(action)) {

                    if (request.getParameter("idedittask") != null) {
                        try {
                            String taskId = request.getParameter("idedittask");
                            String taskName = request.getParameter("edittaskname");
                            String taskDescription = request.getParameter("editdescrip");
                            if (taskName != null && taskDescription != null) {
                                taskName = taskName.trim();
                                taskDescription = taskDescription.trim();
                                iToDoListService.updateItem(taskId, taskName, taskDescription);
                                List<Item> todolist = iToDoListService.getItemsByUserId(userName);
                                request.setAttribute("list", todolist);
                                getServletContext().getRequestDispatcher("/todolist.jsp").include(request, response);
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            ToDoListException e = new ToDoListException("Invalid input: " + ex.getMessage());
                            request.setAttribute("errorMessage", e);
                            getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);

                        } catch (Exception e) {
                            ToDoListException ex = new ToDoListException(e.getMessage());
                            request.setAttribute("errorMessage", ex);
                            getServletContext().getRequestDispatcher("/todoErrorpage.jsp").include(request, response);
                        }
                    }
                    // user did logout
                } else if ("logout".equalsIgnoreCase(action)) {

                    CookieHelper.clearCookies("loginkeep", request, response);
                    CookieHelper.clearCookies("username", request, response);
                    session.invalidate();
                    isAuthenticated = false;
                    response.sendRedirect("/ToDoListWebProject/index.jsp");
                    return;

                }
                break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
