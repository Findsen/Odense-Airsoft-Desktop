package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.Controller;
import sample.Controller.LogController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private final static Logger logger = Logger.getLogger(Controller.class.getName());
    private static LogController lc = new LogController();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));

        Controller.initializeController(primaryStage);
        primaryStage.setTitle("Login Window - Odense Airsoft");
        primaryStage.setScene(new Scene(root, 600,500 ));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException
    {

        launch(args);
    }
}
