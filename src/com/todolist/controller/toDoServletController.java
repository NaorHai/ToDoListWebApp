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
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class toDoServlet
 */
@WebServlet("/todo/*")
public class toDoServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(toDoServletController.class);
    private IToDoListService iToDoListService = new IToDoListServiceImpl();
    private UserService userService = new UserServiceImpl();
    private boolean isLoggerOn = false;

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
        //initialize logger
        if (!isLoggerOn) {
            logger.info("logger is on");
            isLoggerOn = true;
        }

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        boolean auth;

        String email, password, firstName, lastName, title, content, itemId, route, context = "todo";
        String path = request.getParameter("action");
        if (path == null) {

            //checking the next URL from form
            path = request.getPathInfo();
            if (path.contains("login")) path = "/goToLogin";


            else if (path.contains("register")) path = "/goToRegister";
            else if (path.contains("deleteItem")) path = "/deleteItem";
            else if (path.contains("deleteUser")) path = "/deleteUser";
            else if (path.contains("updateItem")) path = "/updateItem";
            else if (path.contains("goToUpdateItem")) path = "/goToUpdateItem";
            else if (path.contains("deleteAllItems")) path = "/deleteAllItems";

        } else {

            //with parameter
            if (path.equals("goToLogin")) path = "/goToLogin";
            else if (path.equals("loginAccount")) path = "/loginAccount";
            else if (path.equals("goToRegister")) path = "/goToRegister";
            else if (path.equals("registerAccount")) path = "/registerAccount";
            else if (path.equals("goToCreateItem")) path = "/goToCreateItem";
            else if (path.equals("createItem")) path = "/createItem";
            else if (path.equals("deleteItem")) path = "/deleteItem";
            else if (path.equals("deleteUser")) path = "/deleteUser";
            else if (path.equals("updateItem")) path = "/updateItem";
            else if (path.equals("goToUpdateItem")) path = "/goToUpdateItem";
            else if (path.equals("deleteAllItems")) path = "/deleteAllItems";

        }

        //getting user authorization
        auth = CookieHelper.getCookieValueByName("auth", request).equalsIgnoreCase("true");

        switch (path) {
            default:
            case "/":
                try {
                    route = (auth) ? "/myZone.jsp" : "/login.jsp";
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
                route = (auth) ? "/myZone.jsp" : "/login.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;


            case "/goToCreateItem":
                route = (auth) ? "/createItem.jsp" : "/login.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;

            case "/logOut":
                CookieHelper.clearCookieByName("tryAgain", request, response);
                CookieHelper.clearCookieByName("auth", request, response);
                route = "/login.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;
            case "/goToUpdateItem":
                itemId = request.getParameter("itemId");
                CookieHelper.createCookie("itemId", itemId, "/", response);
                route = "/updateItem.jsp";
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
                        CookieHelper.createCookie("tryAgain", "true", "/", response);
                        logger.info("credentials are invalid: user: " + email + " with pass: " + password);
                    } else {
                        auth = true;
                        CookieHelper.clearCookieByName("tryAgain", request, response);
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
                    CookieHelper.clearAllUserCookies(request, response);
                    if (!userService.isUserAlreadyExist(email)) {
                        boolean isUserCreated = userService.registerUser(email, password, firstName, lastName);
                        logger.info("Creating a new user " + email + " success: " + isUserCreated);
                        CookieHelper.createCookie("email", email, "/", response);
                        CookieHelper.createCookie("auth", String.valueOf(isUserCreated), "/", response);
                        CookieHelper.createCookie("firstName", firstName, "/", response);
                        CookieHelper.createCookie("lastName", lastName, "/", response);
                        CookieHelper.clearCookieByName("isUserAlreadyExist",request,response);
                        route = (isUserCreated) ? "/goToMyZone" : "/goToRegister";
                        response.sendRedirect("/" + context + route);
                        break;
                    }
                    CookieHelper.createCookie("isUserAlreadyExist", "true", "/", response);
                    route = "/goToRegister";
                    response.sendRedirect("/" + context + route);

                } catch (UserException e) {
                    e.printStackTrace();
                }
                break;
            case "/createItem":
                try {
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    email = CookieHelper.getCookieValueByName("email", request);

                    if (email == null || email.equals("")) {
                        throw new ItemException("user email is missing!");
                    }

                    boolean create = iToDoListService.createItem(email, title, content);
                    route = (create) ? "/goToMyZone" : "/goToMyZone";
                    response.sendRedirect("/" + context + route);
                } catch (ItemException e) {
                    e.printStackTrace();
                }

                break;
            case "/deleteItem":
                try {
                    itemId = request.getParameter("itemId");
                    if (itemId == null || itemId.equals("")) {
                        throw new ItemException("user email is missing!");
                    }

                    boolean deleteItem = iToDoListService.deleteItemById(itemId);
                    route = (deleteItem) ? "/goToMyZone" : "/goToMyZone";
                    response.sendRedirect("/" + context + route);
                } catch (ItemException e) {
                    e.printStackTrace();
                }
            case "/deleteAllItems":
                try {
                    email = request.getParameter("email");
                    if (email == null || email.equals("")) {
                        throw new ItemException("user email is missing!");
                    }

                    boolean deleteItem = iToDoListService.deleteAllItemsByUserId(email);
                    route = (deleteItem) ? "/goToMyZone" : "/goToMyZone";
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

                    boolean deleteUser = userService.deleteUserById(email);
                    route = (deleteUser) ? "/goToRegister" : "/goToRegister";
                    response.sendRedirect("/" + context + route);
                } catch (UserException e) {
                    e.printStackTrace();
                }

                break;
            case "/updateItem":
                try {
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    email = CookieHelper.getCookieValueByName("email", request);
                    itemId = CookieHelper.getCookieValueByName("itemId", request);
                    if (email == null) {
                        throw new ItemException("user email is missing!");
                    }
                    boolean updateItem = iToDoListService.updateItem(email, title, content, itemId);
                    route = (updateItem) ? "/goToMyZone" : "/goToMyZone";
                    response.sendRedirect("/" + context + route);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}