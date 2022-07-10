module com.example.lojadecelular {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.postgresql.jdbc;


    opens fxml to javafx.fxml;


    opens com.example.lojadecelular.controller to javafx.fxml;
    opens com.example.lojadecelular.dao to javafx.fxml;
    opens com.example.lojadecelular.model to javafx.fxml;

    exports com.example.lojadecelular.controller;
    exports com.example.lojadecelular.dao;
    exports com.example.lojadecelular.model;
    exports com.example.lojadecelular.view;
    opens com.example.lojadecelular.view to javafx.fxml;
}