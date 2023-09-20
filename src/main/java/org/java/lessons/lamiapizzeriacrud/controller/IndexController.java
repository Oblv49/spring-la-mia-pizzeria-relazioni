package org.java.lessons.lamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.lamiapizzeriacrud.model.Pizza;
import org.java.lessons.lamiapizzeriacrud.repository.PizzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private PizzeRepository pizzeRepository;

    //index  method
    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzeList = pizzeRepository.findAll();
        model.addAttribute("pizze", pizzeList);

        if (pizzeList.isEmpty()) {
            model.addAttribute("messaggio", "Nessuna pizza disponibile.");
        }

        return "/pizze/List";

    }

    @GetMapping("/show/{pizzaId}")
    public String show(@PathVariable("pizzaId") Integer id, Model model) {
        Optional<Pizza> pizzaOptional = pizzeRepository.findById(id);
        if (pizzaOptional.isPresent()) {
            Pizza pizzaFromDB = pizzaOptional.get();
            model.addAttribute("pizza", pizzaFromDB);
            return "/pizze/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizze/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "pizze/form";
        }
        pizzeRepository.save(pizzaForm);
        return "redirect:/";
    }
}
