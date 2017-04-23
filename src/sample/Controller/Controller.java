package sample.Controller;




import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    //Memberlist
    @FXML private TableView Member_Table;
    @FXML private TableColumn memberIdColumn;
    @FXML private TableColumn first_nameColumn;
    @FXML private TableColumn last_nameColumn;
    @FXML private TableColumn addressColumn;
    @FXML private TableColumn zipCodeColumn;
    @FXML private TableColumn cityColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn phoneColumn;
    @FXML private TableColumn birthdayColumn;
    @FXML private TableColumn memberUntilColumn;
    @FXML private TableColumn idColumn;







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

    public void backMainMenu() throws IOException
    {
        Parent back = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
        Scene mainScene = new Scene(back);
        mainStage.setScene(mainScene);
    }

    public void pressedLogOutButton() throws IOException
    {
        Parent logout = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        Scene mainScene = new Scene(logout);
        mainStage.setScene(mainScene);
    }

    public void pressedMemberList() throws IOException
    {
        Parent memberList = FXMLLoader.load(getClass().getResource("../View/MemberList.fxml"));
        Scene mainScene = new Scene(memberList);
        memberTable();
        mainStage.setScene(mainScene);
    }

    public void memberTable()
    {


    }


}
