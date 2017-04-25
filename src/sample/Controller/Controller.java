package sample.Controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import sample.Model.ODA_Member;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;


public class Controller implements Initializable
{
    private static Stage mainStage;
    private DataBaseController dataBaseController = new DataBaseController();



    // Member
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private DatePicker datepicker_Birthday;

    //Login
    @FXML private PasswordField Login_passwordField;
    @FXML private TextField Login_textField;

    // Mainmenu

    //Memberlist
    @FXML private TableView Member_Table         = new TableView();
    @FXML private TableColumn memberIdColumn     = new TableColumn();
    @FXML private TableColumn first_nameColumn   = new TableColumn();
    @FXML private TableColumn last_nameColumn    = new TableColumn();
    @FXML private TableColumn addressColumn      = new TableColumn();
    @FXML private TableColumn zipCodeColumn      = new TableColumn();
    @FXML private TableColumn cityColumn         = new TableColumn();
    @FXML private TableColumn emailColumn        = new TableColumn();
    @FXML private TableColumn phoneColumn        = new TableColumn();
    @FXML private TableColumn birthdayColumn     = new TableColumn();
    @FXML private TableColumn memberUntilColumn  = new TableColumn();
    @FXML private TableColumn idColumn           = new TableColumn();



    public static void initializeController(Stage stage){
        mainStage = stage;
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
        mainStage.setScene(mainScene);
    }

    public void editableTable()
    {
        Member_Table.setEditable(true);
        System.out.println();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        memberIdColumn.setCellFactory(new PropertyValueFactory<ODA_Member, Integer>("memberId"));
        memberIdColumn.setCellFactory(TextFieldTableCell.<ODA_Member, Integer>forTableColumn(new IntegerStringConverter()));

        first_nameColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("first_Name"));
        first_nameColumn.setCellFactory(forTableColumn());
        first_nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {

            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setFirst_Name(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());

            }
        });



        last_nameColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("last_Name"));
        last_nameColumn.setCellFactory(forTableColumn());

        addressColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("address"));
        addressColumn.setCellFactory(forTableColumn());

        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, Integer>("zipcode"));
        zipCodeColumn.setCellFactory(TextFieldTableCell.<ODA_Member, Integer>forTableColumn(new IntegerStringConverter()));

        cityColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("city"));
        cityColumn.setCellFactory(forTableColumn());

        emailColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("email"));
        emailColumn.setCellFactory(forTableColumn());

        phoneColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("phoneNumber"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        birthdayColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("birthday"));
        birthdayColumn.setCellFactory(forTableColumn());

        memberUntilColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("member_Until"));
        memberUntilColumn.setCellFactory(forTableColumn());

        idColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("id"));
        idColumn.setCellFactory(forTableColumn());

        Member_Table.setItems(dataBaseController.getMember());
        Member_Table.setEditable(false);


    }
}
