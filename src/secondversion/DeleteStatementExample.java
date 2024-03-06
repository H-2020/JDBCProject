package secondversion;

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
        try (Connection connection =  JDBCUtils.getConnection();

             // Stap 2: Maak een verklaring met behulp van het Connection-object
             Statement statement = connection.createStatement();) {
// Stap 3: Het uitvoeren van de update-query
            int result = statement.executeUpdate(DELETE_USERS_SQL);
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {
// druk informatie over SQL-uitzonderingen af
            JDBCUtils.printSQLException(e);
        }
    }

    // Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.

}
