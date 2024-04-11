package com.example.UserJDBC1;

import java.sql.*;

public class UserJDBC1 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_database1?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Fortuner@98"; // Replace with your MySQL password
    
    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // Display existing users
            displayUsers(conn);
            
            // Add a new user
            addUser(conn, "New User", "newuser@example.com");
            
            // Display users after adding a new user
            displayUsers(conn);
            
            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void displayUsers(Connection conn) throws SQLException {
        System.out.println("Existing Users:");
        System.out.println("ID\tName\tEmail");
        System.out.println("-----------------------------");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("email"));
        }
        rs.close();
        stmt.close();
        System.out.println();
    }
    
    private static void addUser(Connection conn, String name, String email) throws SQLException {
        String insertQuery = "INSERT INTO users (name, email) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setString(1, name);
        stmt.setString(2, email);
        int rowsAffected = stmt.executeUpdate();
        System.out.println(rowsAffected + " row(s) inserted.");
        stmt.close();
    }

}
