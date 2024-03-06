package secondversion;

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
        try (Connection connection =  JDBCUtils.getConnection();
// Stap 2: Maak een verklaring met behulp van het Connection-object
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, "Ram");
            preparedStatement.setInt(2, 1);
// Stap 3: Het uitvoeren van de update-query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
// druk informatie over SQL-uitzonderingen af
            JDBCUtils.printSQLException(e);
        }
// Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.
    }

}
