package com.iba.springsecurity.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Librarian {
    private int Librarian_id;
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 4, max = 500, message = "LastName should be between 4 and 500 characters")
    private String last_name;
    @NotEmpty(message = "First name should not be empty")
    @Size(min = 4, max = 500, message = "FirstName should be between 4 and 500 characters")
    private String First_name;
    private String Patronymic;
    @Size(min = 7, message = "Number should be more than 7 symbols")
    private String Phone;
    @NotEmpty(message = "Address should not be empty")
    private String Address;

    public Librarian(){};

    public Librarian(String last_name, String first_name, String patronymic, String phone, String address) {
        this.last_name = last_name;
        First_name = first_name;
        Patronymic = patronymic;
        Phone = phone;
        Address = address;
    }

    public int getLibrarian_id() {
        return Librarian_id;
    }

    public void setLibrarian_id(int librarian_id) {
        Librarian_id = librarian_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
