module ru.itmm.visualcpu {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires spring.context;
    requires spring.expression;
    requires spring.aop;
    requires spring.jcl;
    requires spring.beans;


    opens ru.itmm.visualcpu to javafx.fxml, org.postgresql.jdbc;
    opens ru.itmm.visualcpu.controllers to javafx.fxml;
    opens ru.itmm.visualcpu.models;

    exports ru.itmm.visualcpu;

    exports ru.itmm.visualcpu.controllers;

    exports ru.itmm.visualcpu.models;
    exports ru.itmm.visualcpu.models.memory;
    exports ru.itmm.visualcpu.models.cpu;

    exports ru.itmm.visualcpu.dao;

}