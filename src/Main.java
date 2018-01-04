import com.todolist.dao.HibernateToDoListDAO;
import com.todolist.dao.IToDoListDAO;
import com.todolist.dao.UserDAO;
import com.todolist.dao.UserDAOImpl;
import com.todolist.pojo.Item;
import com.todolist.pojo.User;
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

//        //CREATE user
//        logger.info("Creating a new user " + user.toString()
//                + " success: " + userDAO.saveOrUpdate(user));
//
//        //CREATE item
//        logger.info("Creating a new item " + item.toString()
//                + " success: " + iToDoListDAO.saveOrUpdate(item));
//
//        //READ user
//        User storedUser = userDAO.getUserById(user.getUserId());
//        logger.info("Getting stored user " + storedUser.toString()
//                + " result: " + storedUser);
//
//        //READ item
//        List<Item> userItems = iToDoListDAO.getItemsByUserId(user.getUserId());
//        logger.info("Getting user items " + userItems.get(0).toString()
//                + " result: " + userItems);
//
//        //UPDATE user
//        storedUser.setEmail("new@mail.com");
//        logger.info("Updating a user " + storedUser.toString()
//                + " success: " + userDAO.saveOrUpdate(storedUser));
//
//        //UPDATE item
//        userItems.get(0).setTitle("new title");
//        logger.info("Updating an item " + userItems.get(0).toString()
//                + " success: " + iToDoListDAO.saveOrUpdate(userItems.get(0)));
//
//        //DELETE item
//        logger.info("Deleting User's item: " + item.getItemId()
//                + " success: " + iToDoListDAO.deleteItem(item));
//
//        //DELETE user (also deleting all existed user items
//        logger.info("Deleting user: " + storedUser.getUserId()
//                + " success: " + userDAO.deleteUser(storedUser));
//
//
//        //DELETE all user items
//        User u = new User("user@mail.com", "first", "last", Date.valueOf(LocalDate.now()));
//        Item userItem1 = new Item(u.getUserId(), "title", "content",  Date.valueOf(LocalDate.now()));
//        Item userItem2 = new Item(u.getUserId(), "title", "content",  Date.valueOf(LocalDate.now()));
//
//        userDAO.saveOrUpdate(u);
//        iToDoListDAO.saveOrUpdate(userItem1);
//        iToDoListDAO.saveOrUpdate(userItem2);
//
//        logger.info("User's all items deletion success: "
//                + iToDoListDAO.deleteAllItemsByUserId(u.getUserId()));
    }
}
