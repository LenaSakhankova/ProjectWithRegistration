package com.iba.springsecurity.controllers;

import com.iba.springsecurity.dao.DebtorsDAO;
import com.iba.springsecurity.dao.PersonsDAO;
import com.iba.springsecurity.models.Debtor;
import com.iba.springsecurity.models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/debtors")
public class DebtorsController {

    private DebtorsDAO debtorsDAO;
    private PersonsDAO personsDAO;
    @Autowired
    public DebtorsController(DebtorsDAO debtorsDAO) {
        this.debtorsDAO = debtorsDAO;
    }

    @GetMapping()
    public String index(Model model1) {

        List<Debtor> debtors = new ArrayList<>();
        for (int i = 0; i < debtorsDAO.index().size(); i++){
            debtors.add(debtorsDAO.index().get(i));
        }

        model1.addAttribute("debtors", debtors);

        model1.addAttribute("reader", debtorsDAO.read());
        model1.addAttribute("books", debtorsDAO.book());

        return "debtors/index";

    }


}
