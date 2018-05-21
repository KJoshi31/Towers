package sample;

import HanoiSim.*;
import InputOutputLogic.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        HanoiSim simOne = new HanoiSim(4);
        simOne.runSimulation();
        //simOne.displaySteps();
        //HanoiFileLogic.saveSteps(simOne.getSteps());
        String readSteps = HanoiFileLogic.readSteps();
        simOne.analyzeFileData(readSteps);
    }
}
