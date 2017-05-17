package sample.Controller;


import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import sample.Model.ODA_Member;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;


public class Controller implements Initializable
{

    private static Stage mainStage;
    private final DataBaseController dataBaseController = new DataBaseController();
    private static final LogController lc = new LogController();



    // Member
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private TextField textfield_Adress;
    @FXML private TextField textfield_ZipCode;
    @FXML private TextField textfield_City;
    @FXML private DatePicker datepicker_Birthday;
    @FXML private TextField textfield_Email;
    @FXML private TextField textfield_Phone;
    @FXML private Label label_Saved;


    //Login
    @FXML private PasswordField Login_passwordField;
    @FXML private TextField  Login_textField;


    // Mainmenu

    //Memberlist
    @FXML private TableView <ODA_Member>Member_Table         = new TableView();
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


        if (dataBaseController.adminLogin(Login_textField.getText(),Login_passwordField.getText()))
        {
            Parent login = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
            Scene mainScene = new Scene(login);
            mainStage.setScene(mainScene);
            lc.logController(">>> "+Login_textField.getText()+" has logged in <<<");
        }else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Wrong Login");
            alert.setContentText("Wrong user and/or password");
            alert.showAndWait();
        }

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

    private void clearMember()
    {
        FirstName.clear();
        LastName.clear();
        textfield_Adress.clear();
        textfield_ZipCode.clear();
        textfield_City.clear();
        datepicker_Birthday.getEditor().clear();
        textfield_Email.clear();
        textfield_Phone.clear();
    }

    public void createODAMember() throws IOException
    {
        String msg = " >>CREATED<< "+FirstName.getText()+
                     " " + LastName.getText()+
                     " "+textfield_Adress.getText()+
                     " "+Integer.parseInt(textfield_ZipCode.getText())+
                     " "+textfield_City.getText()+
                     " "+datepicker_Birthday.getValue()+
                     " "+ textfield_Email.getText()+
                     " "+Integer.parseInt(textfield_Phone.getText());

               dataBaseController.createMember(
                FirstName.getText(),
                LastName.getText(),
                textfield_Adress.getText(),
                Integer.parseInt(textfield_ZipCode.getText()),
                textfield_City.getText(),
                datepicker_Birthday.getValue(),
                textfield_Email.getText(),
                Integer.parseInt(textfield_Phone.getText())
        );

        label_Saved.setTextFill(Color.GREEN);
        label_Saved.setText("Medlem\n oprettet \n ");

                PauseTransition delayForLabel = new PauseTransition(Duration.seconds(10));
        delayForLabel.setOnFinished(event -> label_Saved.setText(" "));

        PauseTransition delayForClose = new PauseTransition(Duration.seconds(1));
        delayForClose.setOnFinished(event ->
        clearMember()
        );

        delayForClose.play();

        lc.logController(msg);

    }

    public void deleteMember() throws IOException
    {
        int selectedMember = Member_Table.getSelectionModel().getSelectedIndex();
        ODA_Member odamember = Member_Table.getSelectionModel().getSelectedItem();
        String msg =" >>DELETED<< "+ odamember.toString() ;

        if (selectedMember >= 0)
        {
            Member_Table.getItems().remove(selectedMember);
            dataBaseController.deleteMemberDb(odamember.getMemberId());
            System.out.println(odamember + " er blevet slettet.");
            lc.logController(msg);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
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
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirst_Name(event.getNewValue());


                dataBaseController.updateMember("First_Name",event.getNewValue(),event.getRowValue().getMemberId());

            }
        });


        last_nameColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("last_Name"));
        last_nameColumn.setCellFactory(forTableColumn());
        last_nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setLast_Name(event.getNewValue());


                dataBaseController.updateMember("Last_Name",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        addressColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("address"));
        addressColumn.setCellFactory(forTableColumn());
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setAddress(event.getNewValue());

                dataBaseController.updateMember("Address",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, Integer>("zipcode"));
        zipCodeColumn.setCellFactory(TextFieldTableCell.<ODA_Member, Integer>forTableColumn(new IntegerStringConverter()));
        zipCodeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,Integer>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, Integer> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setZipcode(event.getNewValue());


               dataBaseController.updateMember("Zipcode",String.valueOf(event.getNewValue()),event.getRowValue().getMemberId());
            }
        });

        cityColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("city"));
        cityColumn.setCellFactory(forTableColumn());
        cityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setCity(event.getNewValue());


                dataBaseController.updateMember("City",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        emailColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("email"));
        emailColumn.setCellFactory(forTableColumn());
        emailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setEmail(event.getNewValue());


                dataBaseController.updateMember("Email",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        phoneColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("phoneNumber"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setPhoneNumber(event.getNewValue());

                dataBaseController.updateMember("Phonenumber",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        birthdayColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("birthday"));
        birthdayColumn.setCellFactory(forTableColumn());
        birthdayColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setBirthday(event.getNewValue());


                dataBaseController.updateMember("Birthday",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        memberUntilColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("member_Until"));
        memberUntilColumn.setCellFactory(forTableColumn());
        memberUntilColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setMember_Until(event.getNewValue());


                dataBaseController.updateMember("Member_until",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });

        idColumn.setCellValueFactory(new PropertyValueFactory<ODA_Member, String>("id"));
        idColumn.setCellFactory(forTableColumn());
        idColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ODA_Member,String>>()
        {
            public void handle(TableColumn.CellEditEvent<ODA_Member, String> event)
            {
                String msg = (" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() );
                System.out.println((" >>UPDATE<< FROM: "+ event.getOldValue() +" TO: "+event.getNewValue()+" ON "+ event.getRowValue() ));
                try {
                    lc.logController(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setId(event.getNewValue());

                dataBaseController.updateMember("ID_Card_Number",event.getNewValue(),event.getRowValue().getMemberId());
            }
        });


        Member_Table.setItems(dataBaseController.getMember());
        Member_Table.setEditable(true);

    }

}
