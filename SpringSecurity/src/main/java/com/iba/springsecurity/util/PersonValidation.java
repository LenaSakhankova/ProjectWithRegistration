package com.iba.springsecurity.util;


import com.iba.springsecurity.entity.PersonEntity;
import com.iba.springsecurity.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidation implements Validator {

    private final PersonService personService;

    public PersonValidation(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return PersonEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PersonEntity personEntity = (PersonEntity) target;

        if(personService.checkIfContainsUsername(personEntity.getUsername())){
            errors.rejectValue("username", "", "This username already exist");
        }

    }
}
