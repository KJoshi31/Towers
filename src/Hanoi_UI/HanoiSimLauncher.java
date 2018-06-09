package Hanoi_UI;

import Hanoi_DB.Hanoi_DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HanoiSimLauncher extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Hanoi_DB.connectDB();
        Parent root = FXMLLoader.load(getClass().getResource("TowersOfHanoi.fxml"));
        stage.setTitle("Towers");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        Hanoi_DB.closeConnection();
    }

    public static void main(String[] args){
        launch(args);
    }
}
