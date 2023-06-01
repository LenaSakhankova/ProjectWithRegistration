package com.iba.springsecurity.controllers;


import com.iba.springsecurity.entity.PersonEntity;
import com.iba.springsecurity.services.PersonService;
import com.iba.springsecurity.util.PersonValidation;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping()
public class LoginController {

    private final PersonService personService;
    private final PersonValidation personValidation;


    public LoginController(PersonService personService, PersonValidation personValidation) {
        this.personService = personService;
        this.personValidation = personValidation;
    }


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("personEntity") PersonEntity person){

     return "registration";
    }


    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("personEntity") @Valid PersonEntity person,
                                                                       BindingResult bindingResult){

        personValidation.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "/registration";
        }


        personService.registerNewPerson(person);

        return "login";
    }

    @GetMapping("/adminPage")
    public String showAdminPage(Model model, @ModelAttribute PersonEntity person){

        model.addAttribute("people", personService.receiveAllPeople());
        model.addAttribute("roles", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));

        return "adminPage";


    }


    @PostMapping("assignRole")
    public String assignPerson(@ModelAttribute("personEntity") PersonEntity person,
                               @ModelAttribute("role") String role){

        personService.changeUserRole(person.getId(), role);

        return "redirect:/adminPage";
    }

}
