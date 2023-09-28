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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String doCreateSpecialOff(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
                                     BindingResult bindingResult,
                                     @RequestParam("pizzeId") List<Integer> pizzeId) {
        if (bindingResult.hasErrors()) {
            return "specialOffer/offerForm";
        }
        List<Pizza> pizzeList = pizzeRepository.findAllById(pizzeId);
        specialOfferRepository.save(specialOfferForm);
        for (Pizza pizza : pizzeList) {
            pizza.setSpecialOffer(specialOfferForm);
            pizzeRepository.save(pizza);
        }
        return "redirect:/";
    }

    @GetMapping("/offer-list")
    public String showOfferList(Model model) {
        List<SpecialOffer> specialOffersList = specialOfferRepository.findAll();
        model.addAttribute("specialOffer", specialOffersList);
        if (specialOffersList.isEmpty()) {
            model.addAttribute("messaggio", "Nessuna offerta disponibile.");
        }
        return "/specialOffer/offerList";
    }

}
