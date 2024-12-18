module ru.itmm.visualcpu {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens ru.itmm.visualcpu to javafx.fxml, org.postgresql.jdbc;
    exports ru.itmm.visualcpu;
    opens ru.itmm.visualcpu.controllers to javafx.fxml;
    exports ru.itmm.visualcpu.controllers;

}