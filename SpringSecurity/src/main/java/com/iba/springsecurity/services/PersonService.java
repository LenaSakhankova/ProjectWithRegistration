package com.iba.springsecurity.services;

import com.iba.springsecurity.entity.PersonEntity;
import com.iba.springsecurity.repositories.PersonRepository;
import jakarta.persistence.Version;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {


    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<PersonEntity> receiveAllPeople(){
        return personRepository.findAll();
    }


    @Transactional()
    public void registerNewPerson(PersonEntity personEntity){

        personEntity.setPassword(passwordEncoder.encode(personEntity.getPassword()));
        personEntity.setRole("ROLE_USER");

        personRepository.save(personEntity);
    }


    @Transactional(readOnly = true)
    public boolean checkIfContainsUsername(String username){

        return personRepository.findByUsername(username).isPresent();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void changeUserRole(Long id, String role){;

       Optional<PersonEntity> person = personRepository.findById(id);
       if(person.isPresent()){
           person.get().setRole(role);
           personRepository.save(person.get());
       }

    }
}
