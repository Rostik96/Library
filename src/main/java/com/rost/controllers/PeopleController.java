package com.rost.controllers;

import com.rost.dao.PersonDAO;
import com.rost.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/new")
    public String createPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.createPerson(person);
        return "redirect:/people/" + personDAO.getCurrentMaxId();
    }

    @GetMapping()
    public String readPeople(Model model) {
        model.addAttribute("people", personDAO.readPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String readPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.readPersonById(id));
        return "people/show";
    }

    @GetMapping("/edit/{id}")
    public String updatePerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.readPersonById(id));
        return "people/edit";
    }

    @PatchMapping()
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.updatePerson(person);
        return "redirect:/people/" + person.getId();
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        boolean hasBooks = personDAO.personHasBooks(id);
        if (hasBooks) {
            return "redirect:/people/" + id;
        }
        personDAO.deletePersonById(id);
        return "redirect:/people";
    }
}