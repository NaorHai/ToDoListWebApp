import com.todoList.pojo.User;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


/**
 * Created by Papushe on 14/12/2017.
 */
public class Main {
    public static void main(String[] args) {

        //creating factory for getting sessions
        SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
        //creating a new session for adding products
        Session session = factory.openSession();
        session.beginTransaction();
        User u1 = new User(UUID.randomUUID(), "pap@pap.com", "pap", "Ushe", LocalDate.now());
        User u2 = new User(UUID.randomUUID(), "naor@naor.com", "nao", "r", LocalDate.now());
        User u3 = new User(UUID.randomUUID(), "tomer@tomer.com", "kt", "zv", LocalDate.now());

        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.getTransaction().commit();
        session.close();
        //creating a new session for getting all products
        Session anotherSession = factory.openSession();
        anotherSession.beginTransaction();
        List products = anotherSession.createQuery("from User").list();
        System.out.println("There are " + products.size() + " product(s)");
        Iterator i = products.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
        anotherSession.close();
    }
}




//public class Main {
//    public static void main(String[] args) {
//        String script = "resources/DBinit.sql";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            new ScriptRunner(DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/ToDo", "root", "shely9188"))
//                    .runScript(new BufferedReader(new FileReader(script)));
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }
//}
