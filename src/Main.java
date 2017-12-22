import com.todoList.pojo.Item;
import com.todoList.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;


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


        User u1 = new User("pap@pap.com", "pap", "Ushe", Date.valueOf(LocalDate.now()));
        User u2 = new User("pap@pap.com", "pap", "Ushe", Date.valueOf(LocalDate.now()));
        User u3 = new User("pap@pap.com", "pap", "Ushe", Date.valueOf(LocalDate.now()));;


        Item i1 = new Item(u1.getUserId(),"Task","Content", Date.valueOf(LocalDate.now()));
        Item i2 = new Item(u2.getUserId(),"Task","Content", Date.valueOf(LocalDate.now()));
        Item i3 = new Item(u3.getUserId(),"Task","Content", Date.valueOf(LocalDate.now()));


        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.save(i1);
        session.save(i2);
        session.save(i3);


        session.getTransaction().commit();
        session.close();
        //creating a new session for getting all products
        Session anotherSession = factory.openSession();
        anotherSession.beginTransaction();

        List users = anotherSession.createQuery("from User").list();
        System.out.println("There are " + users.size() + " user(s)");
        Iterator i = users.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }

        List items = anotherSession.createQuery("from User").list();
        System.out.println("There are " + items.size() + " item(s)");
        Iterator j = items.iterator();
        while(j.hasNext()) {
            System.out.println(j.next());
        }
        anotherSession.close();
    }
}
