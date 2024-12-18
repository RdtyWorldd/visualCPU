package ru.itmm.visualcpu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.itmm.visualcpu.controllers.IObserver;
import ru.itmm.visualcpu.models.ProgramModel;

public class HelloController implements IObserver<ProgramModel> {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void event(ProgramModel model) {

    }
}