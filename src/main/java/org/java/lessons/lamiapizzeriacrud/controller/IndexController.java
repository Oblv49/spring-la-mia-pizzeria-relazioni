package org.java.lessons.lamiapizzeriacrud.controller;

import org.java.lessons.lamiapizzeriacrud.model.Pizza;
import org.java.lessons.lamiapizzeriacrud.repository.PizzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private PizzeRepository pizzaRepository;

    //metodo index
    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzeList = pizzaRepository.findAll();
        model.addAttribute("pizze", pizzeList);
        return "/Pizze/List";
    }
}
