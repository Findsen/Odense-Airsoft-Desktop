package sample.Controller;




import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller{



    public static Stage mainStage;
    // Member
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private DatePicker datepicker_Birthday;

    //Login
    @FXML private PasswordField Login_passwordField;
    @FXML private TextField Login_textField;

    // Mainmenu




    public static void initializeController(Stage stage){
        mainStage = stage;
    }

   public void send (ActionEvent event)
    {
        System.out.println(FirstName.getText()+" " + LastName.getText()+" " + datepicker_Birthday.getValue());
    }

    public void clear(ActionEvent event) throws IOException
    {
        FirstName.clear();
        LastName.clear();



    }

    public void createMember() throws IOException
    {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("../View/CreateMemberPage.fxml"));
        Scene mainScene = new Scene(mainMenu);
        mainStage.setScene(mainScene);
    }

    public void loginPressed() throws IOException
    {
        Parent login = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
        Scene mainScene = new Scene(login);
        mainStage.setScene(mainScene);
    }
}
