package sample.Model;


/**
 * Created by Christian Findsen on 17-04-2017.
 */
public class ODA_Member
{

    private int memberId;
    private String first_Name;
    private String last_Name;
    private String address;
    private int zipcode;
    private String city;
    private String email;
    private String phoneNumber;
    private String birthday;
    private String member_Until;
    private String id;

    public ODA_Member(
            int memberId,
            String first_Name,
            String last_Name,
            String address,
            int zipcode,
            String city,
            String email,
            String phoneNumber,
            String birthday,
            String member_Until,
            String id)
    {

        this.memberId = memberId;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.member_Until = member_Until;
        this.id = id;
    }

    public int getMemberId()
    {
        return memberId;
    }

    public void setMemberId(int memberId)
    {
        this.memberId = memberId;
    }

    public String getFirst_Name()
    {
        return first_Name;
    }

    public void setFirst_Name(String first_Name)
    {
        this.first_Name = first_Name;
    }

    public String getLast_Name()
    {
        return last_Name;
    }

    public void setLast_Name(String last_Name)
    {
        this.last_Name = last_Name;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getMember_Until()
    {
        return member_Until;
    }

    public void setMember_Until(String member_Until)
    {
        this.member_Until = member_Until;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return (memberId + " " + first_Name + " " + last_Name + " " + address + " " + zipcode
                + " " + city + " " + phoneNumber + " " + birthday + " " + member_Until + " " + id);
    }
}