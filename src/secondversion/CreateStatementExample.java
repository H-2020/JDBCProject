package secondversion;

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
        try (Connection connection = JDBCUtils.getConnection();
             // Stap 2: Maak een verklaring aan met behulp van het Connection-object
             Statement statement = connection.createStatement();) {

            // Stap 3: De query of update query uitvoeren
            statement.execute(createTableSQL);

        } catch (SQLException e) {

            // druk SQL exception informatie af
            JDBCUtils.printSQLException(e);
        }

        // Stap 4: try-with-resource statement zal de verbinding automatisch sluiten.
    }

}
