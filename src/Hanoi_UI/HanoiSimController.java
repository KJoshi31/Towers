package Hanoi_UI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import HanoiSim.HanoiSim;

import javafx.event.ActionEvent;
import java.lang.Math;

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

    private void resetControls(){

    }

    @FXML
    private void runSimulation(ActionEvent event){
        hSimObject = new HanoiSim(diskNumber);
        hSimObject.runSimulation();
        stepsTextArea.setText(hSimObject.getSteps());
    }

}