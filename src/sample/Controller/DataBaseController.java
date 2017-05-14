package sample.Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Model.ODA_Member;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Christian Findsen on 23-04-2017.
 */
class DataBaseController
{
    private String url = "jdbc:mysql://localhost:3306?useSSL=false";
    private String user = "root";
    private String pass = "Nastusha0309";
    private Connection myConnection;


    private void connectionForDB()
    {
        try {

            myConnection = DriverManager.getConnection(url,user,pass);


        }catch (Exception exc)
        {
            exc.printStackTrace();
        }


    }

    public ObservableList<ODA_Member> getMember()
    {
        ObservableList<ODA_Member> ODAMembers = FXCollections.observableArrayList();

        try {


            connectionForDB();

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

    public void updateMember(String column, String correction, int pos)
    {
        try {
           connectionForDB();

            Statement myStmt = myConnection.createStatement();

            myStmt.executeUpdate("UPDATE mydb.memberlist SET "+column+" = "+"\'"+ correction+"\'"+" WHERE Member_ID = "+pos+";");

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public ObservableList<ODA_Member> searchMember(String firstName, String lastName)
    {
        ObservableList<ODA_Member> searchODA = FXCollections.observableArrayList();

        searchODA.clear();
        try {
           connectionForDB();

            // Creating the statement for the SQL Server
            Statement myStmt = myConnection.createStatement();

            // Creating the mySQL Query to be executed
            ResultSet myRes = myStmt.executeQuery ("SELECT *" +
                    " FROM mydb.memberlist WHERE First_Name = '"+firstName+"' OR Last_Name = '"+lastName+"';");

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
                searchODA.add
                        (
                                new ODA_Member(
                                        Member_ID, first_name, last_name,
                                        address, zipcode, city, email, phonenumber,
                                        birthday, member_until,id)
                        );

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return searchODA;
    }

    public void createMember(String firstName, String lastName, String address, int zipCode, String city, LocalDate date, String email, int phone)
    {

        try {
            connectionForDB();

            PreparedStatement myStmt = myConnection.prepareStatement(
                    "INSERT INTO mydb.memberlist(First_Name,Last_Name,Address,Zipcode,City," +
                            "Email,Phonenumber,Birthday, Member_until, Member_ID) "+
                            "VALUES(" +
                            "'"+firstName+"'" +
                            ",'"+lastName+"'," +
                            "'"+address+"'," +
                            "'"+zipCode+"'," +
                            "'"+city+"'," +
                            "'"+email+"'," +
                            "'"+phone+"'," +
                            "'"+date+"'," +
                            "'"+date+"'," +
                            "'0')");

            myStmt.executeUpdate();

        }catch (Exception e)
        {
            e.printStackTrace();
        }




    }

    public void deleteMemberDb(int id)
    {
        try {
            connectionForDB();

            Statement myStmt = myConnection.createStatement();

            myStmt.executeUpdate("DELETE From mydb.memberlist WHERE Member_ID = "+id+";");

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}


