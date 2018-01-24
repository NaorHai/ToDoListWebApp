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
import java.io.IOException;
import java.io.PrintWriter;

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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();
        IToDoListService iToDoListService = new IToDoListServiceImpl();


        //check path and do action
        String path = request.getPathInfo();
        RequestDispatcher dispatcher = null;
        PrintWriter writer = response.getWriter();
//		writer.flush();
        switch (path) {

            case "/login":
                String userName = request.getParameter("username");
                String userPass = request.getParameter("password");
                if (userService.checkUserLogin(userName, userPass)){
                    request.setAttribute("username", userName);
                    request.setAttribute("password", userPass);
                    getServletContext().getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
                else{
                    dispatcher = request.getRequestDispatcher("WEB-INF/index.html");
                    dispatcher.forward(request, response);
                }
                break;

            case "/register":
                dispatcher = getServletContext().getRequestDispatcher("WEB-INF/myToDoList.jsp");
                dispatcher.forward(request, response);
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
