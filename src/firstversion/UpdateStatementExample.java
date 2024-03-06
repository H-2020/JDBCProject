package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class UpdateStatementExample {

    private static final String UPDATE_USERS_SQL = "update users set name = ? where id = ?;";
    public static void main(String[] argv) throws SQLException {
        UpdateStatementExample updateStatementExample = new UpdateStatementExample();
        updateStatementExample.updateRecord();
    }
    public void updateRecord() throws SQLException {
        System.out.println(UPDATE_USERS_SQL);
// Stap 1: Een verbinding maken naar de DB
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root",
                        "");
// Stap 2: Maak een verklaring met behulp van het Connection-object
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, "Ram");
            preparedStatement.setInt(2, 1);
// Stap 3: Het uitvoeren van de update-query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
// druk informatie over SQL-uitzonderingen af
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
                    t
                            = t.getCause();
                }
            }
        }
    }
}
