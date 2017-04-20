package sample.Model;

import javafx.scene.control.DatePicker;

/**
 * Created by Christian Findsen on 17-04-2017.
 */
public class Member
{

    private String firstName;
    private String lastName;
    private String address;
    private int zipcode;
    private String city;
    private DatePicker datePicker;
    private String email;

    public Member(String firstName, String lastName, String address, int zipcode, String city, DatePicker datePicker, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.datePicker = datePicker;
        this.email = email;

    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(int zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public DatePicker getDatePicker()
    {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker)
    {
        this.datePicker = datePicker;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
