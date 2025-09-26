package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/hotel";
    private static String user = "postgres";
    private static String password = "postgres";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            System.out.println("Error DB" + e.getMessage());
        }
        return conn;
    }
}
