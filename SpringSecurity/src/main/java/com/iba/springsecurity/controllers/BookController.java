package com.iba.springsecurity.controllers;


import com.iba.springsecurity.dao.BooksDAO;
import com.iba.springsecurity.dao.PersonsDAO;
import com.iba.springsecurity.models.Book;
import com.iba.springsecurity.models.Reader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BookController {
    private BooksDAO bookDAO;

    private PersonsDAO personDAO;

    @Autowired
    public BookController(BooksDAO bookDAO, PersonsDAO personDAO)
    {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Reader reader) {

        model.addAttribute("book", bookDAO.show(id));

        Optional<Reader> bookOwner = bookDAO.getBookOwner(id);

        if(bookOwner.isPresent())
        {
            model.addAttribute("owner", bookOwner.get());
        }
        else {model.addAttribute("people", personDAO.index());}
        return "book/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";

        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id)
    {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Reader reader)
    {
        bookDAO.assign(id, reader);
        return "redirect:/books/" + id;
    }
    @GetMapping("/bookInUse")
    public String bookInUse(Model model)
    {
        model.addAttribute("books", bookDAO.bookInUse());
        return "book/bookInUse";
    }
}
