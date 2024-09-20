package ltwst2.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {
    private final String serverName = "localhost";
    protected final String dbName = "ltwst2";
    private final String portNumber = "1433";
    private final String instance = "";  // Để trống nếu không có instance
    private final String userID = "sa";
    private final String password = "123456";

    public Connection getConnection() throws Exception{
       
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;

            if (instance == null || instance.trim().isEmpty()) 
                url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url,userID, password);

        }
		

    public static void main(String[] args) {
        try {
            Connection connection = new DBConnectSQL().getConnection();
            if (connection != null) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
