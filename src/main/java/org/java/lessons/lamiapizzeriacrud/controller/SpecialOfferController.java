package org.java.lessons.lamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.lamiapizzeriacrud.model.Pizza;
import org.java.lessons.lamiapizzeriacrud.model.SpecialOffer;
import org.java.lessons.lamiapizzeriacrud.repository.PizzeRepository;
import org.java.lessons.lamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;
    @Autowired
    private PizzeRepository pizzeRepository;

    @GetMapping("/special-offer")
    public String createSpecialOff(Model model) {
        model.addAttribute("specialOffer", new SpecialOffer());
        List<Pizza> pizze = pizzeRepository.findAll();
        model.addAttribute("pizze", pizze);
        return "specialOffer/offerForm";
    }

    @PostMapping("/special-offer")
    public String doCreateSpecialOff(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "specialOffer/offerForm";
        }
        specialOfferRepository.save(specialOfferForm);
        return "redirect:/";
    }
}
