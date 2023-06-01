package com.iba.springsecurity.controllers;

import com.iba.springsecurity.dao.LibrarianDAO;
import com.iba.springsecurity.models.Librarian;
import com.iba.springsecurity.util.LibrarianValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/librarians")
public class LibrarianController {
    private LibrarianDAO librarianDAO;
    private LibrarianValidation librarianValidation;

    @Autowired
    public LibrarianController(LibrarianDAO librarianDAO, LibrarianValidation librarianValidation)
    {
        this.librarianDAO = librarianDAO;
        this.librarianValidation=librarianValidation;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("librarians", librarianDAO.index());
        return "librarians/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("librarian", librarianDAO.show(id));

        return "librarians/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("librarians") Librarian librarian) {
        return "librarians/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("librarians") @Valid Librarian librarian,
                         BindingResult bindingResult) {

        librarianValidation.validate(librarian, bindingResult);

        if (bindingResult.hasErrors())
            return "librarians/new";

        librarianDAO.save(librarian);
        return "redirect:/librarians";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("librarian", librarianDAO.show(id));
        return "librarians/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("librarian") @Valid Librarian librarian, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "librarians/edit";

        librarianDAO.update(id, librarian);
        return "redirect:/librarians";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        librarianDAO.delete(id);
        return "redirect:/adminPage";
    }
}
