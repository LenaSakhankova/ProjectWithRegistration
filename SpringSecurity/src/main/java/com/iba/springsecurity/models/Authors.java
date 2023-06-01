package com.iba.springsecurity.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Authors {
    private int id;
    private Integer bookId;
    private Integer publishingId;
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 4, max = 500, message = "Last name should be between 4 and 500 characters")
    private String lastName;
    private String firstName;
    private String patronymic;
    private Integer yearOfBirth;
    @Max(value = 2023, message = "Year of Birthday should be greater than 1910")
    private Integer yearOfDeath;

    public Authors(){};

    public Authors(Integer bookId, Integer publishingId, String lastName, String firstName, String patronymic, Integer yearOfBirth,
                   Integer yearOfDeath) {
        this.bookId = bookId;
        this.publishingId = publishingId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(Integer publishingId) {
        this.publishingId = publishingId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getYearOfDeath() {
        return yearOfDeath;
    }

    public void setYearOfDeath(Integer yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }
}
