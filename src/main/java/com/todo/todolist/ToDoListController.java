package com.todo.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ToDoListController {
    @FXML
    private TextField taskInput;

    private ListView<String> taskList;

    private TodoListDAO todoListDAO;

    public ToDoListController() {
        this.todoListDAO = new TodoListDAO();
    }

    @FXML
    public void initialize() {
        loadTasks();
    }


    @FXML
    private void addTask() {
        String taskDescription = taskInput.getText().trim();
        if (!taskDescription.isEmpty()) {
            todoListDAO.addTask(taskDescription);
            loadTasks();
            taskInput.clear();
        }
    }

    @FXML
    private void markCompleted() {
        String selectedTask = taskList.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            todoListDAO.markTaskCompleted(selectedTask);
            loadTasks();
        }
    }

    @FXML
    private void removeTask() {
        String selectedTask = taskList.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            todoListDAO.removeTask(selectedTask);
            loadTasks();
        }
    }
    private void loadTasks() {
        taskList.getItems().clear();
        taskList.getItems().addAll(todoListDAO.getTasks());
}
}
