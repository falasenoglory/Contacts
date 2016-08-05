package com.sjimenez.contacts.Object;

/**
 * Created by Shanyl Jimenez on 8/4/2016.
 */
public class Contacts {

    private String FirstName;
    private String LastName;
    private String ContactNo;

    public Contacts() {
    }

    public Contacts(String firstName, String lastName, String contactNo) {
        FirstName = firstName;
        LastName = lastName;
        ContactNo = contactNo;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }
}
