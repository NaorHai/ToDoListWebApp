import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;


/**
 * Created by Papushe on 14/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        String script = "../resources/DBinit.sql";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            new ScriptRunner(DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ToDo", "root", "shely9188"))
                    .runScript(new BufferedReader(new FileReader(script)));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
