module com.todo.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.todo.todolist to javafx.fxml;
    exports com.todo.todolist;
}