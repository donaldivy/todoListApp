package com.todo.todolist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws
            SQLException{
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

}
