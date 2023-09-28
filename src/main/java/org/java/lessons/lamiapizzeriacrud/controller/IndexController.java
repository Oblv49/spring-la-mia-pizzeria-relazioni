package org.java.lessons.lamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.lamiapizzeriacrud.model.Pizza;
import org.java.lessons.lamiapizzeriacrud.model.SpecialOffer;
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
            SpecialOffer specialOffer = pizzaFromDB.getSpecialOffer();
            model.addAttribute("specialOffer", specialOffer);
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzeRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizze/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza pizzaForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizze/edit";
        }
        pizzeRepository.save(pizzaForm);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        pizzeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q") String searchString, Model model) {
        List<Pizza> filteredPizzaList = pizzeRepository.findByNameContaining(searchString);
        if (filteredPizzaList.isEmpty()) {
            model.addAttribute("noResults", true);
        }
        model.addAttribute("pizze", filteredPizzaList);
        return "pizze/List";
    }


}
