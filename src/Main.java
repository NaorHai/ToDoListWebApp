import com.todoList.dao.HibernateToDoListDAO;
import com.todoList.dao.IToDoListDAO;
import com.todoList.dao.UserDAO;
import com.todoList.dao.UserDAOImpl;
import com.todoList.pojo.Item;
import com.todoList.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


/**
 * Created by Papushe on 14/12/2017.
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        UserDAO userDAO = UserDAOImpl.getInstance();
        IToDoListDAO iToDoListDAO = HibernateToDoListDAO.getInstance();

        User user = new User(
                "user@mail.com",
                "first",
                "last",
                Date.valueOf(LocalDate.now()));

        Item item = new Item(
                user.getUserId(),
                "title",
                "content of item",
                Date.valueOf(LocalDate.now()));

        //CREATE
        userDAO.saveOrUpdate(user);
        iToDoListDAO.saveOrUpdate(item);

        //READ

        //UPDATE

        //DELETE
    }
}
