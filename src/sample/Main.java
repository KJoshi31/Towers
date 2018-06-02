package sample;

import HanoiSim.*;
import IO_Utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //max disk amount is 22
        System.out.println("Enter Amount of Disks:");
        Scanner input = new Scanner(System.in);
        int diskNum = Integer.parseInt( input.next().trim() );

        HanoiSim simOne = new HanoiSim(diskNum);

        HanoiFileLogic.exportSimConfig(Arrays.asList(2,4,6,20));

        int[] configList = HanoiFileLogic.importSimConfig();


    }
}
