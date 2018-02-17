package com.todolist.controller; /**
 * Created by Haimov on 14/12/2017.
 */

import com.todolist.exception.item.ItemException;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.Item;
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
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class toDoServlet
 */
@WebServlet("/todo")
public class toDoServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(toDoServletController.class);
    private UserService userService = new UserServiceImpl();
    private IToDoListService iToDoListService = new IToDoListServiceImpl();


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

        RequestDispatcher dispatcher = null;
        boolean isRegistrationSucceed = false;
        boolean isUserAuthenticated = false;
        String path = request.getParameter("action");
        path = (path == null) ? "/" : "/" + path.toLowerCase();

        switch (path) {
            default:case "/":
            try{
                dispatcher = getServletContext().getRequestDispatcher("/todo.jsp");
                dispatcher.forward(request, response);
                break;
            }
            catch(Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            case "/login":
                try{
                    dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    try {
                        isUserAuthenticated = userService.checkUserLogin(email, password);
                        request.setAttribute("authentication", isUserAuthenticated);
                        break;


                    }catch (UserException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
                catch(Exception e)
                {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

            case "/register":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                try{
                    isRegistrationSucceed = userService.registerUser(email,password,firstName,lastName);
                    if (!isRegistrationSucceed) {
                        throw new UserException("User registration failed!");
                    }
                    dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                catch(UserException e)
                {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

            case "/getitems":
                if (!isUserAuthenticated) break;

                try {
                     email = request.getParameter("email");
                     dispatcher = getServletContext().getRequestDispatcher("/userPage.jsp");
                     List<Item> userItems = iToDoListService.getItemsByUserId(email);
                     request.setAttribute("email", email);
                     request.setAttribute("userItems", userItems);
                     dispatcher.forward(request, response);
                     break;

                 }catch (ItemException e) {
                     logger.error(e.getMessage());
                     e.printStackTrace();
                 }

            case "/getuser":
                if (!isUserAuthenticated) break;

                try {
                    email = request.getParameter("email");
                    dispatcher = getServletContext().getRequestDispatcher("/userPage.jsp");
                    User user = userService.getUserById(email);
                    request.setAttribute("email", email);
                    request.setAttribute("userDetails", user);
                    dispatcher.forward(request, response);
                    break;

                }catch (UserException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            case "/updateuser": break;
            case "/deleteuser": break;
            case "/deleteitem": break;
            case "/createitem": break;
            case "/updateitem": break;

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
