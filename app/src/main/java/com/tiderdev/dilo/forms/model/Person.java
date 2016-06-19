package com.tiderdev.dilo.forms.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dwikadarmawan on 6/19/16.
 */
public class Person {

    private String firstName, lastName, email, address, city, postalCode;

    public Person() {}

    public Person(HashMap<String, String> person) {
        this.firstName = person.get("firstName");
        this.lastName = person.get("lastName");
        this.email = person.get("email");
        this.address = person.get("address");
        this.city = person.get("city");
        this.postalCode = person.get("postalCode");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
