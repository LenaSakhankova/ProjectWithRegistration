package com.iba.springsecurity.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Full name should not be empty")
    @Size(min = 4, max = 1000, message = "Full name should be between 1-1000 characters")
    private String name;
    @Min(value = 1500, message = "Год больше 1500")
    private int year;

    private String shortDescription;
    private int storageLocation;
    private int tom;
    private Integer personId;

    public Book(){};

    public Book(String name, int year, String shortDescription, int storageLocation, int tom, int personId) {
        this.name = name;
        //this.author = author;
        this.year = year;
        this.shortDescription = shortDescription;
        this.storageLocation = storageLocation;
        this.tom = tom;
        this.personId = personId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;
    }

    public int getTom() {
        return tom;
    }

    public void setTom(int tom) {
        this.tom = tom;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

//    public String getAuthor() {
//        return author;
//    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
