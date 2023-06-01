package com.iba.springsecurity.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class Reader {
    private int id;

    @NotEmpty(message = "Full name should not be empty")
    @Size(min = 4, max = 500, message = "Full name should be between 4 and 500 characters")
    private String FIO;

    @Min(value = 1910, message = "Year of Birthday should be greater than 1910")
    private int yearOfBirth;

    @Size(min = 7, message = "Number should be more than 7 symbols")
    private String phone_number;



    public Reader() {

    }

    public Reader(String FIO, int yearOfBirth, String phone_number) {
        this.FIO = FIO;
        this.yearOfBirth = yearOfBirth;
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
