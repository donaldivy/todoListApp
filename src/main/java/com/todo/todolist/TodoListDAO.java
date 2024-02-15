package com.todo.todolist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListDAO {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS tasks (id INT AUTO_INCREMENT PRIMARY KEY, description TEXT, completed BOOLEAN)";
    private static final String INSERT_TASK_QUERY = "INSERT INTO tasks (description, completed) VALUES (?, false)";
    private static final String UPDATE_TASK_QUERY = "UPDATE tasks SET completed = true WHERE description = ?";
    private static final String DELETE_TASK_QUERY = "DELETE FROM tasks WHERE description = ?";
    private static final String SELECT_TASKS_QUERY = "SELECT description FROM tasks WHERE completed = false";

    public TodoListDAO() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TASK_QUERY)) {
            statement.setString(1, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markTaskCompleted(String description) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_QUERY)) {
            statement.setString(1, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTask(String description) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TASK_QUERY)) {
            statement.setString(1, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTasks() {
        List<String> tasks = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_TASKS_QUERY)) {
            while (resultSet.next()) {
                tasks.add(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}