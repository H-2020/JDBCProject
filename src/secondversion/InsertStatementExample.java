package secondversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertStatementExample {
    private static final String INSERT_USERS_SQL = "INSERT INTO users" +
            "  (id, name, email, country, password) VALUES " +
            " (?, ?, ?, ?, ?);";

    public static void main(String[] args) throws SQLException {
        InsertStatementExample createTableExample = new InsertStatementExample();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
// Stap 1: Een verbinding naar de DB
        try (Connection connection =  JDBCUtils.getConnection();
// Stap 2: Maak een verklaring aan met behulp van het verbindingsobject
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "John");
            preparedStatement.setString(3, "john@doe.com");
            preparedStatement.setString(4, "BE");
            preparedStatement.setString(5, "secretpass");
            System.out.println(preparedStatement);
// Stap 3: Het uitvoeren van de update-query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
// druk SQL-uitzonderingsinformatie af
            JDBCUtils.printSQLException(e);
        }
// Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.
    }


}


