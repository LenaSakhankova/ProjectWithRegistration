package com.iba.springsecurity.util;

import com.iba.springsecurity.dao.PersonsDAO;
import com.iba.springsecurity.models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReaderValidation implements Validator {
    private final PersonsDAO personDAO;

    @Autowired
    public ReaderValidation(PersonsDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Reader.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Reader reader = (Reader) o;

        if(personDAO.getPersonByFIO(reader.getFIO()).isPresent()){
            errors.rejectValue("FIO", "", "Человек уже существует!");
        }
    }
}
