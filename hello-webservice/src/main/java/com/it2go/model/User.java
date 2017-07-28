package com.it2go.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(UserAdapter.class)
public interface User {

    public String getFirstName();
    public String getLastName();
    default String getFullName(){return this.getFirstName() + " " + this.getLastName();}
}
