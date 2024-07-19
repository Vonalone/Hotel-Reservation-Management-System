import java.sql.* ;
public class ConnexionJV {


    public static Connection openConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            String protocol = "jdbc:mysql:";

            String hostIp = "localhost";
            String port = "3306";

            String dbName = "DataHotel";

            String connectionString = protocol + "//" + hostIp + ":" + port + "/" + dbName;


            String username = "root";
            String password = "7007";


            Connection connection = DriverManager.getConnection(connectionString, username, password);

            return connection;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


}
