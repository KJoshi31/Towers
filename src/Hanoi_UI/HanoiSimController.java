package Hanoi_UI;
import IO_Utils.HanoiFileLogic;
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
    private void runSimulation(ActionEvent event){
        disableControls();

        hSimObject = new HanoiSim(diskNumber);
        hSimObject.runSimulation();
        steps = hSimObject.getSteps();
        stepsTextArea.setText(steps);
    }

    @FXML
    private void resetSimulation(ActionEvent event){
        disableControls();
        stepsTextArea.clear();
        hSimObject = null;
    }

    @FXML
    private void saveLoadSteps(ActionEvent event){
        Button selectedButton = (Button) event.getSource();
        String buttonID = selectedButton.getId();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("HT file (*.ht)", "*.ht");

        fileChooser.getExtensionFilters().add(extensionFilter);

        Node source = (Node) event.getSource();
        Window currentStage = source.getScene().getWindow();

        File file = null;

        if(buttonID.equals("saveStepsButton")) {
            file = fileChooser.showSaveDialog(currentStage);
            if (file != null) {
                System.out.println(file);
            }
        }else if(buttonID.equals("loadStepsButton")){
            file = fileChooser.showOpenDialog(currentStage);

            if(file != null){
                System.out.println(file);

            }
        }else if(buttonID.equals("saveConfigButton")){
            file = fileChooser.showSaveDialog(currentStage);
            if (file != null) {
                System.out.println(file);
                HanoiFileLogic.exportSimConfig(List.of(diskNumber), file);
            }

        }else if(buttonID.equals("loadConfigButton")){
            file = fileChooser.showOpenDialog(currentStage);

            if(file != null){
                System.out.println(file);

            }
        }
    }

}