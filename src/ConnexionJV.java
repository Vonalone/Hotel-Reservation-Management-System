import java.sql.* ;
public class ConnexionJV {

    // Establishes a database connection
    public static Connection openConnection() {
        try {
            // Load the JDBC driver class by its name
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection protocol
            String protocol = "jdbc:mysql:";
            // Host IP address and port
            String hostIp = "localhost";  // Depends on the context
            String port = "3306";  // Default MySQL port
            // Database name
            String dbName = "datahotels";  // Depends on the context
            // Connection string
            String connectionString = protocol + "//" + hostIp + ":" + port + "/" + dbName;

            // Connection credentials
            String username = "root";  // Depends on the context
            String password = "7007";  // Depends on the context

            // Establish the connection
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            return connection;
        } catch (Exception e) {
            // Exception handling
            e.printStackTrace();
            return null;
        }
    }


}
