package org.learning.lamiapizzeria.controller;

import jakarta.validation.Valid;
import org.learning.lamiapizzeria.PizzaNotFoundException;
import org.learning.lamiapizzeria.model.Pizza;
import org.learning.lamiapizzeria.model.SpecialOffer;
import org.learning.lamiapizzeria.service.PizzaService;
import org.learning.lamiapizzeria.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/special-offer")
public class SpecialOfferController {
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private SpecialOfferService specialOfferService;

    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId") Integer id, Model model) {

        try {
            Pizza pizzaSpecialOffer = pizzaService.getPizzaById(id);
            SpecialOffer specialOffer = new SpecialOffer();
            specialOffer.setPizza(pizzaSpecialOffer);
            specialOffer.setStartDate(LocalDate.now());
            specialOffer.setEndDate(LocalDate.now().plusMonths(1));
            model.addAttribute("specialOffer", specialOffer);
            return "SpecialOffer/create";
        } catch (PizzaNotFoundException e) {
            return "redirect:/pizza";
        }


    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute SpecialOffer formSpecialOffer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "SpecialOffer/create";
        SpecialOffer specialOfferNuova = specialOfferService.store(formSpecialOffer);
        return "redirect:/pizza/" + specialOfferNuova.getPizza().getId();
    }
}
