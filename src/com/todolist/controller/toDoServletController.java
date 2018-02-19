package com.todolist.controller;
/**
 * Created by Haimov on 14/12/2017.
 */
import com.todolist.configuration.CookieHelper;
import com.todolist.exception.item.ItemException;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.Item;
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

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        String route;
        boolean auth = Boolean.valueOf(CookieHelper.getCookieValueByName("auth", request));

//      String path = request.getParameter("action");
        String path = request.getServletPath();
        path = (request.getServletPath() == null) ? "/" : "/" + path.toLowerCase();

        switch (path) {
            default:case "/":
            try{
                route = (auth) ? "/userPage.jsp" : "/login.jsp";
                dispatcher = getServletContext().getRequestDispatcher(route);
                dispatcher.forward(request, response);
                break;
            }
            catch(Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            case "/login":
                try {
                    if (auth) {
                        dispatcher = getServletContext().getRequestDispatcher("/userPage.jsp");
                        dispatcher.forward(request, response);
                    }

                    String email = request.getParameter("email");
                    String password = request.getParameter("password");

                    try {
                        auth = userService.checkUserLogin(email, password);
                        request.setAttribute("auth", auth);
                        CookieHelper.createCookie("auth", String.valueOf(auth), "/", response);
                        session.setAttribute("email", email);
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
                    boolean isRegistered = userService.registerUser(email,password,firstName,lastName);
                    if (!isRegistered) {
                        throw new UserException("User registration failed!");
                    }
                    CookieHelper.createCookie("auth", "true", "/", response);
                    session.setAttribute("email", email);
                    dispatcher = getServletContext().getRequestDispatcher("/userPage.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                catch(UserException e)
                {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

            case "/userlist":
                if (!auth) {
                    dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                try {
                     email = session.getAttribute("email").toString();
                     dispatcher = getServletContext().getRequestDispatcher("/userPage.jsp");
                     List<Item> userItems = iToDoListService.getItemsByUserId(email);
                     request.setAttribute("userItems", userItems);
                     dispatcher.forward(request, response);
                     break;
                 }
                 catch (ItemException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
