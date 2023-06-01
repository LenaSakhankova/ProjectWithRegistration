package com.iba.springsecurity.util;

import com.iba.springsecurity.dao.LibrarianDAO;
import com.iba.springsecurity.dao.PersonsDAO;
import com.iba.springsecurity.models.Librarian;
import com.iba.springsecurity.models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class LibrarianValidation implements Validator {
    private final LibrarianDAO librarianDAO;

    @Autowired
    public LibrarianValidation(LibrarianDAO librarianDAO) {
        this.librarianDAO = librarianDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Librarian.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Librarian librarian = (Librarian) o;

        if(librarianDAO.getPersonByFIO(librarian.getLast_name()).isPresent()){
            errors.rejectValue("FI", "1", "Человек уже существует!");
        }
    }
}
