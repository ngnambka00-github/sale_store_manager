package com.ltnc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectData {
    
    public static Connection getConnection() {
        Connection conn = null;
        
        String url = "jdbc:mysql://localhost:3306/sale_manager";
        String username = "root";
        String password = "dgsMUE35";
        
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error Database Connection");
        }
        return conn;
    }
}
