package com.iba.springsecurity.controllers;

import com.iba.springsecurity.dao.PublishingDAO;
import com.iba.springsecurity.models.Authors;
import com.iba.springsecurity.models.Debtor;
import com.iba.springsecurity.models.Publishing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/publishing")
public class PublishingController {
    private PublishingDAO publishingDAO;

    @Autowired
    public PublishingController(PublishingDAO publishingDAO) {
        this.publishingDAO = publishingDAO;
    }

    @GetMapping()
    public String index(Model model1) {
        model1.addAttribute("pub", publishingDAO.index());
        model1.addAttribute("author", publishingDAO.authPublishing());
        return "publishing/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("publ", publishingDAO.show(id));

        return "publishing/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("publ", publishingDAO.show(id));
        return "publishing/edit";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        publishingDAO.delete(id);
        return "redirect:/publishing";
    }
}
