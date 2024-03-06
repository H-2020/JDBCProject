package firstversion;

import java.sql.SQLException;
import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;


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
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root",
                        "");
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
            printSQLException(e);
        }
// Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.
    }

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


