package org.java.lessons.lamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.java.lessons.lamiapizzeriacrud.model.Pizza;
import org.java.lessons.lamiapizzeriacrud.model.SpecialOffer;
import org.java.lessons.lamiapizzeriacrud.repository.PizzeRepository;
import org.java.lessons.lamiapizzeriacrud.repository.SpecialOfferRepository;
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

    @GetMapping("/edit-offer/{id}")
    public String editSpecialOffer(@PathVariable("id") Integer id, Model model) {
        Optional<SpecialOffer> specialOfferOptional = specialOfferRepository.findById(id);
        if (specialOfferOptional.isPresent()) {
            SpecialOffer specialOffer = specialOfferOptional.get();
            model.addAttribute("specialOffer", specialOffer); // Assicurati di aggiungere l'offerta speciale al model
            List<Pizza> pizze = pizzeRepository.findAll();
            model.addAttribute("pizze", pizze);
            return "specialOffer/editOfferForm";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit-offer/{id}")
    public String doEditSpecialOffer(@PathVariable("id") Integer id,
                                     @Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
                                     BindingResult bindingResult,
                                     @RequestParam("pizzeId") List<Integer> pizzeId) {
        if (bindingResult.hasErrors()) {
            return "specialOffer/editOfferForm";
        }
        Optional<SpecialOffer> specialOfferOptional = specialOfferRepository.findById(id);
        if (specialOfferOptional.isPresent()) {
            SpecialOffer specialOffer = specialOfferOptional.get();
            specialOffer.setStartDate(specialOfferForm.getStartDate());
            specialOffer.setFinishDate(specialOfferForm.getFinishDate());
            specialOffer.setOfferTitle(specialOfferForm.getOfferTitle());

            List<Pizza> pizzeList = pizzeRepository.findAllById(pizzeId);
            specialOfferRepository.save(specialOffer);

            for (Pizza pizza : specialOffer.getPizze()) {
                pizza.setSpecialOffer(null);
                pizzeRepository.save(pizza);
            }
            for (Pizza pizza : pizzeList) {
                pizza.setSpecialOffer(specialOffer);
                pizzeRepository.save(pizza);
            }

            return "redirect:/offer-list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete-offer/{id}")
    public String deleteSpecialOffer(@PathVariable("id") Integer id) {
        Optional<SpecialOffer> specialOfferOptional = specialOfferRepository.findById(id);
        if (specialOfferOptional.isPresent()) {
            SpecialOffer specialOffer = specialOfferOptional.get();
            for (Pizza pizza : specialOffer.getPizze()) {
                pizza.setSpecialOffer(null);
                pizzeRepository.save(pizza);
            }
            specialOfferRepository.delete(specialOffer);
            return "redirect:/offer-list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
