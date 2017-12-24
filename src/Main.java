import com.todoList.dao.HibernateToDoListDAO;
import com.todoList.dao.IToDoListDAO;
import com.todoList.dao.UserDAO;
import com.todoList.dao.UserDAOImpl;
import com.todoList.pojo.Item;
import com.todoList.pojo.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Papushe on 14/12/2017.
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
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
        logger.info("Creating a new user" + user.toString());
        userDAO.saveOrUpdate(user);

        logger.info("Creating a new item" + item.toString());
        iToDoListDAO.saveOrUpdate(item);

        //READ
        User storedUser = userDAO.getUserById(user.getUserId());
        logger.info("Getting stored user" + storedUser.toString());

        List<Item> userItems = iToDoListDAO.getItemsByUserId(user.getUserId());
        logger.info("Getting user items" + userItems.get(0).toString());

        //UPDATE


        //DELETE
        boolean isUserDeleted = userDAO.deleteUser(storedUser);
        logger.info("User deletion success " + isUserDeleted);

        boolean isItemDeleted = userDAO.deleteUser(storedUser);
        logger.info("User's item deletion success " + isItemDeleted);

    }
}
