package com.iba.springsecurity.controllers;

import com.iba.springsecurity.dao.ScheduleDAO;
import com.iba.springsecurity.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleDAO scheduleDAO;
    @Autowired
    public ScheduleController(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("sch", scheduleDAO.index());
        return "/schedule";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("schedule") Schedule schedule,
                         BindingResult bindingResult) {

        scheduleDAO.create(schedule);
        return "/scheduleNew";
    }
}
