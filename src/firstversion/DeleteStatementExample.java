package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementExample {

    private static final String DELETE_USERS_SQL = "delete from users where id = 3;";

    public static void main(String[] argv) throws SQLException {
        DeleteStatementExample deleteStatementExample = new DeleteStatementExample();
        deleteStatementExample.deleteRecord();
    }

    public void deleteRecord() throws SQLException {
        System.out.println(DELETE_USERS_SQL);
// Stap 1: Een verbinding maken naar de DB
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "");


             // Stap 2: Maak een verklaring met behulp van het Connection-object
             Statement statement = connection.createStatement();) {
// Stap 3: Het uitvoeren van de update-query
            int result = statement.executeUpdate(DELETE_USERS_SQL);
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {
// druk informatie over SQL-uitzonderingen af
            printSQLException(e);
        }
    }

    // Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t
                            = t.getCause();
                }
            }
        }
    }
}
