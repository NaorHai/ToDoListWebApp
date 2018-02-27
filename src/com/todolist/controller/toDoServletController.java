package com.todolist.controller;
/**
 * Created by Haimov on 14/12/2017.
 */

import com.todolist.configuration.CookieHelper;
import com.todolist.exception.item.ItemException;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import com.todolist.service.IToDoListService;
import com.todolist.service.IToDoListServiceImpl;
import com.todolist.service.UserService;
import com.todolist.service.UserServiceImpl;
import org.apache.log4j.Logger;

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
 * Servlet implementation class toDoServlet
 */
@WebServlet("/todo/*")
public class toDoServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(toDoServletController.class);

    private IToDoListService iToDoListService = new IToDoListServiceImpl();

    private UserService userService = new UserServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public toDoServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        String route;
        String context = "todo";
        boolean auth;
        String email, password, firstName, lastName, title, content, taskId;
        String path = request.getParameter("action");
        if (path == null) {

            //checking the next URL from form
            path = request.getPathInfo();
            if (path.contains("login")) path = "/goToLogin";
            else if (path.contains("register")) path = "/goToRegister";
            else if (path.contains("deleteTask")) path = "/deleteTask";
            else if (path.contains("deleteUser")) path = "/deleteUser";

        } else {

            //with parameter
            if (path.equals("goToLogin")) path = "/goToLogin";
            else if (path.equals("loginAccount")) path = "/loginAccount";
            else if (path.equals("goToRegister")) path = "/goToRegister";
            else if (path.equals("registerAccount")) path = "/registerAccount";
            else if (path.equals("goToCreateTask")) path = "/goToCreateTask";
            else if (path.equals("createTask")) path = "/createTask";
            else if (path.equals("deleteTask")) path = "/deleteTask";
            else if (path.equals("deleteUser")) path = "/deleteUser";

        }

        //getting user authorization
        auth = CookieHelper.getCookieValueByName("auth", request).equalsIgnoreCase("true");

        switch (path) {
            default:case "/":
                try {
//                    route = (auth) ? "/login.jsp" : "/login.jsp";
                    route = "/login.jsp";
                    dispatcher = getServletContext().getRequestDispatcher(route);
                    dispatcher.forward(request, response);
                    break;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

            case "/goToLogin":
                route = "/login.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;

            case "/goToRegister":
                route = "/register.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;

            case "/goToMyZone":
                route = (auth) ? "/myZone.jsp" : "/login.jsp" ;
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;


            case "/goToCreateTask":
                route =  (auth) ? "/createTask.jsp" : "/login.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;

            case "/logOut":
                route = "/login.jsp";
                CookieHelper.clearAllUserCookies(request, response);
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;

            case "/loginAccount":
                User user;
                email = request.getParameter("email");
                password = request.getParameter("password");

                try {
                    user = userService.checkUserLogin(email, password);

                    if (user == null) {
                        auth = false;
                        logger.info("credentials are invalid: user: " + email + " with pass: " + password);
                    }
                    else {
                        auth = true;
                        CookieHelper.createCookie("email", email, "/", response);
                        CookieHelper.createCookie("firstName", user.getFirstName(), "/", response);
                        CookieHelper.createCookie("lastName", user.getLastName(), "/", response);
                    }

                    CookieHelper.createCookie("auth", String.valueOf(auth), "/", response);
                    logger.info("Login user " + email + " success: " + auth);

                    route = (auth) ? "/goToMyZone" : "/goToLogin";
                    response.sendRedirect("/" + context + route);
                    break;

                } catch (UserException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
                break;

            case "/registerAccount":
                email = request.getParameter("email");
                password = request.getParameter("password");
                firstName = request.getParameter("firstName");
                lastName = request.getParameter("lastName");

                try {
                    boolean isUserCreated = userService.registerUser(email, password, firstName, lastName);
                    logger.info("Creating a new user " + email + " success: " + isUserCreated);
                    CookieHelper.createCookie("email", email, "/", response);
                    CookieHelper.createCookie("auth", String.valueOf(isUserCreated), "/", response);

                    route = (isUserCreated) ? "/goToMyZone" : "/goToRegister";
                    response.sendRedirect("/" + context + route);
                } catch (UserException e) {
                    e.printStackTrace();
                }

                break;

            case "/createTask":
                try {
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    email = CookieHelper.getCookieValueByName("email", request);

                    if (email == null) {
                        throw new ItemException("user email is missing!");
                    }

                    boolean create = iToDoListService.createItem(email, title, content);
                    route = (create) ? "/goToMyZone" : "/goToRegister";
                    response.sendRedirect("/" + context + route);
                } catch (ItemException e) {
                    e.printStackTrace();
                }

                break;
            case "/deleteTask":
                try {
                    taskId = request.getParameter("taskId");


                    if (taskId == null) {
                        throw new ItemException("user email is missing!");
                    }

                    boolean create= iToDoListService.deleteItemById(taskId);
                    route = (create) ? "/goToMyZone" : "/goToRegister";
                    response.sendRedirect("/" + context + route);
                } catch (ItemException e) {
                    e.printStackTrace();
                }

                break;
            case "/deleteUser":
                try {
                    email = request.getParameter("email");


                    if (email == null) {
                        throw new UserException("user email is missing!");
                    }

                    boolean create= userService.deleteUserById(email);
                    route = (create) ? "/goToRegister" : "/goToRegister";
                    response.sendRedirect("/" + context + route);
                } catch (UserException e) {
                    e.printStackTrace();
                }

                break;

//            case "/login":
//                try {
////                    if (auth) {
//                    dispatcher = getServletContext().getRequestDispatcher("/createTask.jsp");
//                    dispatcher.forward(request, response);
////                    }
//
//                    String email = request.getParameter("email");
//                    String password = request.getParameter("password");
//
//                    auth = userService.checkUserLogin(email, password);
//                    request.setAttribute("auth", auth);
//                    CookieHelper.createCookie("auth", String.valueOf(auth), "/", response);
//                    session.setAttribute("email", email);
//                    break;
//
//                } catch (Exception e) {
//                    logger.error(e.getMessage());
//                    e.printStackTrace();
//                }


//                dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
//                dispatcher.forward(request, response);
//                String email = request.getParameter("email");
//                String password = request.getParameter("password");
//                String firstName = request.getParameter("firstName");
//                String lastName = request.getParameter("lastName");
//
//                try {
//                    boolean isRegistered = userService.registerUser(email, password, firstName, lastName);
//                    if (!isRegistered) {
//                        throw new UserException("User registration failed!");
//                    }
//                    CookieHelper.createCookie("auth", "true", "/", response);
//                    session.setAttribute("email", email);
//                    dispatcher = getServletContext().getRequestDispatcher("/createTask.jsp");
//                    dispatcher.forward(request, response);
//                    break;
//                } catch (UserException e) {
//                    logger.error(e.getMessage());
//                    e.printStackTrace();
//                }

            case "/userlist":
//                if (!auth) {
//                    dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
//                    dispatcher.forward(request, response);
//                    break;
//                }
//                try {
//                    email = session.getAttribute("email").toString();
//                    dispatcher = getServletContext().getRequestDispatcher("/createTask.jsp");
//                    List<Item> userItems = iToDoListService.getItemsByUserId(email);
//                    request.setAttribute("userItems", userItems);
//                    dispatcher.forward(request, response);
//                    break;
//                } catch (ItemException e) {
//                    logger.error(e.getMessage());
//                    e.printStackTrace();
//                }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}