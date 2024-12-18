module ru.itmm.visualcpu {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;


    opens ru.itmm.visualcpu to javafx.fxml;
    exports ru.itmm.visualcpu;
    opens ru.itmm.visualcpu.controllers to javafx.fxml;
    exports ru.itmm.visualcpu.controllers;
}