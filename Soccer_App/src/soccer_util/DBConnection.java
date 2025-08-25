package soccer_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // هنا حط اسم جهازك (hostname) أو IP
    private static final String HOST = "DESKTOP-PJA0NAO";
    private static final String PORT = "1433";             // بورت SQL Server
    private static final String DB_NAME = "SoccerDB";      // اسم قاعدة البيانات
    private static final String USER = "sa";               // اسم المستخدم
    private static final String PASSWORD = "Aef11873Aef11873Aef11873Aef11873Aef11873"; // الباسورد

    private static final String URL =
            "jdbc:sqlserver://" + HOST + ":" + PORT + ";" +
                    "databaseName=" + DB_NAME + ";" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";

    // الميثود الأساسية لفتح connection
    public static Connection getConnection() throws SQLException {
        try {
            // تحميل درايفر SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("🔹 Trying to connect to SQL Server...");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        }
    }
}
