package sample.Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.ODA_Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Christian Findsen on 23-04-2017.
 */
public class DataBaseController
{

    public static ObservableList<ODA_Member> dateBaseController()
    {

        ObservableList<ODA_Member> ODAMembers = FXCollections.observableArrayList();

        try {
            System.out.println("Establising a connection for database");

            Connection myConnection = DriverManager.getConnection
                    (
                            "jdbc:mysql://localhost:3306", "root", "Nastusha0309"
                    );

            // Creating the statement for the SQL Server
            Statement myStmt = myConnection.createStatement();

            // Creating the mySQL Query to be executed
            ResultSet myRes = myStmt.executeQuery ("SELECT Member_ID, First_Name,Last_Name,Address,Zipcode," +
                    "City,Email,Phonenumber,Birthday,Member_until, ID_Card_Number" +
                    " FROM mydb.memberlist");

            while (myRes.next()) {

                // Retrieving the info from the database
                int Member_ID = myRes.getInt("Member_ID");
                String first_name = myRes.getString("First_Name");
                String last_name = myRes.getString("Last_Name");
                String address = myRes.getString("Address");
                int zipcode = myRes.getInt("Zipcode");
                String city = myRes.getString("City");
                String email = myRes.getString("Email");
                String phonenumber = myRes.getString("Phonenumber");
                String birthday = myRes.getString("Birthday");
                String member_until = myRes.getString("Member_until");
                String id = myRes.getString("ID_Card_Number");

                //Building an ODAMembers object with all these parameters
                ODAMembers.add
                        (
                                new ODA_Member(
                                        Member_ID, first_name, last_name,
                                        address, zipcode, city, email, phonenumber,
                                        birthday, member_until,id)
                        );

            }

        }catch (Exception exc)
        {
           exc.printStackTrace();
        }

        return ODAMembers;
    }
}
