package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.itmm.visualcpu.App;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.parser.ProgramTextParser;

import java.io.File;
import java.io.IOException;

public class MainController implements IObserver<ProgramModel> {
    private ExecutorModel executor = BExecutorModel.model();
    private ProgramModel program = BProgramModel.model();
    @FXML
    private GridPane programPanel;
    @FXML
    private GridPane rightPane;
    @FXML
    private Button fileButton;
    @FXML
    private Button addButton;
    @FXML
    private Button debugButton;
    @FXML
    private Button startButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button resetButton;


    @FXML
    void initialize()
    {
        program.addObserver(this);
        nextButton.setDisable(false);
        stopButton.setDisable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("memory-view.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(
                App.class.getResource("register-view.fxml"));
        try {
            Pane pane = fxmlLoader.load();
            rightPane.addRow(1, pane);
            Pane pane1 = fxmlLoader1.load();
            rightPane.addRow(0, pane1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onButtonFileClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );
        Pane dialogPane = new Pane();
        Scene scene = new Scene(dialogPane);
        File file = fileChooser.showOpenDialog(dialogPane.getScene().getWindow());
        System.out.println(file);
        if(file == null)
            return;

        ProgramTextParser parser = new ProgramTextParser();
        try {
            parser.parseProgram(file);
            program.eventCall();
        } catch (IOException e) {
            //добавить диалоговое окно
            System.err.println(e);
        }
    }

    @FXML
    private void onButtonAddClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("addCommand-view.fxml"));

        try {
            Pane dialogPane;
            dialogPane = fxmlLoader.load();
            Scene scene = new Scene(dialogPane);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onButtonDebugClick() {
        if(executor.isDebug())
            executor.setDebug(false);
        else
            executor.setDebug(true);
    }

    @FXML
    private void onButtonStartClick() {
        if(executor.isDebug()){
            startButton.setDisable(true);
            nextButton.setDisable(false);
            stopButton.setDisable(false);
            executor.setProgramLength(program.getCommandCount());
            executor.eventCall();
        }
        else {
            executor.executeAll(program);
            nextButton.setDisable(true);
            stopButton.setDisable(true);
            startButton.setDisable(false);
        }
    }

    @FXML
    private void onButtonNextClick() {
        try {
            executor.executeNext(program);
        } catch (RuntimeException e) {
            nextButton.setDisable(true);
            stopButton.setDisable(true);
            startButton.setDisable(false);
        }
    }

    @FXML
    private void onButtonStopClick() {
        startButton.setDisable(false);
        nextButton.setDisable(true);
        stopButton.setDisable(true);
        executor.reset();
    }

    @FXML
    private void onButtonResetClick() {
        nextButton.setDisable(true);
        stopButton.setDisable(true);
        startButton.setDisable(false);
        executor.removeObservers();
        program.reset();
    }

    @Override
    public void event(ProgramModel model) {
        programPanel.getChildren().clear();
        int i = 0;
        for(Command c : model) {
            CommandController cm = new CommandController(c, i);
            FXMLLoader fxmlLoader = new FXMLLoader(
                    App.class.getResource("command-view.fxml"));
            fxmlLoader.setController(cm);
            try {
                Pane pane = fxmlLoader.load();
                programPanel.addColumn(0, pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}
