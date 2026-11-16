import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao()
            throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlserver://localhost:1433;"
                + "instanceName=SQLEXPRESS;"
                + "databaseName=aulajava;"
                + "encrypt=true;trustServerCertificate=true;"
                + "integratedSecurity=true;";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }
}
