package com.it2go.model;

import javax.xml.bind.annotation.XmlType;


@XmlType(name = "User")
public class UserImpl implements User {
    private String firstName;
    private String lastName;

    public UserImpl() {
    }
    public UserImpl(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String s) {
        lastName = s;
    }
    public String getLastName() {
        return lastName;
    }

}