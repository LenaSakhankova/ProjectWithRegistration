package com.iba.springsecurity.services;

import com.iba.springsecurity.entity.PersonEntity;
import com.iba.springsecurity.repositories.PersonRepository;
import com.iba.springsecurity.secutity.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<PersonEntity> personEntity = personRepository.findByUsername(username);

        if(personEntity.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }

        return new PersonDetails(personEntity.get());
    }


}
