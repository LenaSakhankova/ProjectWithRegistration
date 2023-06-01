package com.iba.springsecurity.controllers;

import com.iba.springsecurity.dao.PersonsDAO;
import com.iba.springsecurity.models.Reader;
import com.iba.springsecurity.util.ReaderValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonsDAO personDAO;

    private ReaderValidation personValidator;

    @Autowired
    public PeopleController(PersonsDAO personDAO, ReaderValidation personValidator)
    {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("reader") Reader reader) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("reader") @Valid Reader reader,
                         BindingResult bindingResult) {

        personValidator.validate(reader, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(reader);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("reader", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, reader);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}