package org.learning.lamiapizzeria.controller;

import jakarta.validation.Valid;
import org.learning.lamiapizzeria.PizzaNotFoundException;
import org.learning.lamiapizzeria.model.Pizza;
import org.learning.lamiapizzeria.repository.PizzaRepository;
import org.learning.lamiapizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {

        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaService.getAllPizzas();
        } else {
            pizzas = pizzaService.getFilteredPizzas(keyword.get());
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("keyword", keyword.orElse(""));
        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id, RedirectAttributes attr) {

        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            model.addAttribute("onePizza", pizza);
            return "pizza/pizza-detail";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id " + id + " non trovata.", e);
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizza/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "pizza/create";

        //salva su db

        pizzaService.storePizza(formPizza);

        return "redirect:/pizza";
    }


    @GetMapping("/error")
    public String error() {
        return "error/4xx";
    }
}
