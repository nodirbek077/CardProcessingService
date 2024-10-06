package uz.asgardia.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleTestConnection {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "system";
        String password = "1qazxsw2@N";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");
            connection.close();
        }catch (Exception e){
            System.out.println("Error: " + e.getCause() + ", " + e.getMessage());
        }
    }
}
