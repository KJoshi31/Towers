package Hanoi_UI;
import Hanoi_DB.Hanoi_DB;
import IO_Utils.HanoiFileLogic;
import IO_Utils.MethodTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import HanoiSim.HanoiSim;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.lang.Math;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HanoiSimController {

    @FXML private Button saveStepsButton;
    @FXML private Button loadStepsButton;
    @FXML private Button saveConfigButton;
    @FXML private Button loadConfigButton;
    @FXML private Button runSimButton;
    @FXML private Button resetSimButton;
    @FXML private Slider diskSlider;
    @FXML private TextField disksTextField;
    @FXML private TextArea stepsTextArea;

    //hanoiSim object used for other operations
    //diskNumber used for functionality
    private HanoiSim hSimObject;
    private int diskNumber = 1;
    private String steps;

    public void initialize(){
        disksTextField.textProperty().setValue(Integer.toString(diskNumber));


        diskSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {

                        if(diskNumber!= newValue.intValue()){
                            diskNumber = (int) Math.ceil(newValue.intValue());
                            System.out.println(diskNumber);
                            disksTextField.textProperty().setValue(Integer.toString(diskNumber));
                        }
                    }
                });
    }

    private void disableControls(){
        resetSimButton.setDisable(!resetSimButton.isDisabled());
        runSimButton.setDisable(!runSimButton.isDisabled());
        diskSlider.setDisable(!diskSlider.isDisabled());
        saveStepsButton.setDisable(!saveStepsButton.isDisabled());
        saveConfigButton.setDisable(!saveConfigButton.isDisabled());
        loadConfigButton.setDisable(!loadConfigButton.isDisabled());
        loadStepsButton.setDisable(!loadStepsButton.isDisabled());
    }

    @FXML
    private void runSimulation(ActionEvent event) throws SQLException {
        disableControls();

        hSimObject = new HanoiSim(diskNumber);

        long milliseconds = MethodTimer.timeSimulation(hSimObject);
        steps = hSimObject.getSteps();

        Hanoi_DB.insertSteps(diskNumber,steps);
        Hanoi_DB.insertSimTim(diskNumber,milliseconds);

        HanoiFileLogic analyzer = new HanoiFileLogic();
        String analysis = analyzer.analyzeFileData(steps);
        String globalAvg =  Hanoi_DB.getAverageMilliseconds(diskNumber);
        stepsTextArea.setText(analysis+"\n"+globalAvg+"\n"+"-----\n"+steps);
    }

    @FXML
    private void resetSimulation(ActionEvent event){
        disableControls();
        stepsTextArea.clear();
        diskSlider.valueProperty().setValue(1);
        disksTextField.textProperty().setValue(Integer.toString(1));
        steps = "";
        hSimObject = null;
    }

    @FXML
    private void saveLoadSteps(ActionEvent event){
        Button selectedButton = (Button) event.getSource();
        String buttonID = selectedButton.getId();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter stepExt =
                new FileChooser.ExtensionFilter("HT file (*.hStep)", "*.hStep");

        FileChooser.ExtensionFilter configExt =
                new FileChooser.ExtensionFilter("HT file (*.hConfig)", "*.hConfig");

        FileChooser.ExtensionFilter textExt =
                new FileChooser.ExtensionFilter("txt file (*.txt)", "*.txt");

        File file = null;

        if(buttonID.equals("saveStepsButton")) {
            fileChooser.getExtensionFilters().add(stepExt);
            fileChooser.getExtensionFilters().add(textExt);

            Node source = (Node) event.getSource();
            Window currentStage = source.getScene().getWindow();

            file = fileChooser.showSaveDialog(currentStage);
            if (file != null) {
                System.out.println(file);
                HanoiFileLogic.saveSteps(steps,file);
            }
        }else if(buttonID.equals("loadStepsButton")){
            fileChooser.getExtensionFilters().add(stepExt);
            fileChooser.getExtensionFilters().add(textExt);

            Node source = (Node) event.getSource();
            Window currentStage = source.getScene().getWindow();

            file = fileChooser.showOpenDialog(currentStage);

            if(file != null){
                System.out.println(file);
                disableControls();
                steps = HanoiFileLogic.readSteps(file);

                HanoiFileLogic analyzer = new HanoiFileLogic();
                String analysis =  analyzer.analyzeFileData(steps);

                stepsTextArea.setText(analysis+"\n"+"-----\n"+steps);
                disksTextField.setText("N/A");


            }
        }else if(buttonID.equals("saveConfigButton")){
            fileChooser.getExtensionFilters().add(configExt);

            Node source = (Node) event.getSource();
            Window currentStage = source.getScene().getWindow();

            file = fileChooser.showSaveDialog(currentStage);
            if (file != null) {
                System.out.println(file);
                HanoiFileLogic.exportSimConfig(List.of(diskNumber), file);
            }

        }else if(buttonID.equals("loadConfigButton")){
            fileChooser.getExtensionFilters().add(configExt);

            Node source = (Node) event.getSource();
            Window currentStage = source.getScene().getWindow();

            file = fileChooser.showOpenDialog(currentStage);

            if(file != null){
                System.out.println(file);
                diskNumber = HanoiFileLogic.importSimConfig(file)[0];
                System.out.println(diskNumber);

                diskSlider.valueProperty().setValue(diskNumber);
                disksTextField.textProperty().setValue(Integer.toString(diskNumber));
            }
        }
    }

}