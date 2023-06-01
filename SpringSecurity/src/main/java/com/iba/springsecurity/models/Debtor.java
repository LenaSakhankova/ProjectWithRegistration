package com.iba.springsecurity.models;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Debtor {
    private int id;
    private int bookId;
    private int personId;
    private int delay;

    public Debtor(int bookId, int personId, int delay) {
        this.bookId = bookId;
        this.personId = personId;
        this.delay = delay;
    }

    public Debtor(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Reader person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Book book;

    public Reader getPerson() {
        return person;
    }

    public void setPerson(Reader person) {
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
