package sample.Controller;


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

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;


public class Controller implements Initializable
{
    private static Stage mainStage;
    private DataBaseController dataBaseController = new DataBaseController();



    // Member
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private TextField textfield_Adress;
    @FXML private TextField textfield_ZipCode;
    @FXML private TextField textfield_City;
    @FXML private DatePicker datepicker_Birthday;
    @FXML private TextField textfield_Email;


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

    @FXML private TextField search_FirstName;
    @FXML private TextField search_LastName;



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

    public void refresh()
    {
        Member_Table.setItems(dataBaseController.getMember());

    }

    public void searchODA()
    {
        Member_Table.setItems(dataBaseController.searchMember(search_FirstName.getText(),search_LastName.getText()));
    }

    public void createODAMember()
    {


        dataBaseController.createMember(FirstName.getText(),
                LastName.getText(),
                textfield_Adress.getText(),
                Integer.parseInt(textfield_ZipCode.getText()),
                textfield_City.getText(),
                (datepicker_Birthday.getEditor()).getText(),
                textfield_Email.getText());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        first_nameColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("first_Name"));
        first_nameColumn.setCellFactory(forTableColumn());
        first_nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setFirst_Name(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("First_Name",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });


        last_nameColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("last_Name"));
        last_nameColumn.setCellFactory(forTableColumn());
        last_nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setLast_Name(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("Last_Name",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        addressColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("address"));
        addressColumn.setCellFactory(forTableColumn());
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setAddress(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("Address",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, Integer>("zipcode"));
        zipCodeColumn.setCellFactory(TextFieldTableCell.<ODA_Member, Integer>forTableColumn(new IntegerStringConverter()));
        zipCodeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,Integer>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, Integer> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setZipcode(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

               dataBaseController.updateMember("Zipcode",String.valueOf(event.getNewValue()),event.getRowValue().getMemberId());
            }
        });

        cityColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("city"));
        cityColumn.setCellFactory(forTableColumn());
        cityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCity(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("City",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        emailColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("email"));
        emailColumn.setCellFactory(forTableColumn());
        emailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setEmail(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("Email",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        phoneColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("phoneNumber"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPhoneNumber(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("Phonenumber",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        birthdayColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("birthday"));
        birthdayColumn.setCellFactory(forTableColumn());
        birthdayColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setBirthday(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("Birthday",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        memberUntilColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("member_Until"));
        memberUntilColumn.setCellFactory(forTableColumn());
        memberUntilColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setMember_Until(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("Member_until",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        idColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("id"));
        idColumn.setCellFactory(forTableColumn());
        idColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                ((ODA_Member) event.getTableView().getItems().get(event.getTablePosition().getRow())).setId(event.getNewValue());
                System.out.println("Ændrer "+ event.getOldValue()+" til: "+event.getNewValue());
                System.out.println(event.getRowValue().getMemberId());

                dataBaseController.updateMember("ID_Card_Number",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        Member_Table.setItems(dataBaseController.getMember());
        Member_Table.setEditable(true);


    }
}
