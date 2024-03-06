package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatementExample {
    private static final String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
            "  name varchar(20),\r\n" + "  email varchar(20),\r\n" + "  country varchar(20),\r\n" +
            "  password varchar(20)\r\n" + "  );";
            
    public static void main(String[] argv) throws SQLException {
        CreateStatementExample createTableExample = new CreateStatementExample();
        createTableExample.createTable();
    }
    public void createTable() throws SQLException {

        System.out.println(createTableSQL);
        // Step 1: Een connectie maken door de DB(SSL=Secure Sockets Layer)
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "");

             // Stap 2: Maak een verklaring aan met behulp van het Connection-object
             Statement statement = connection.createStatement();) {

            // Stap 3: De query of update query uitvoeren
            statement.execute(createTableSQL);

        } catch (SQLException e) {

            // druk SQL exception informatie af
            printSQLException(e);
        }

        // Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }

}
}
