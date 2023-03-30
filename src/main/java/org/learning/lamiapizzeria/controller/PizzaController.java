package org.learning.lamiapizzeria.controller;

import org.learning.lamiapizzeria.model.Pizza;
import org.learning.lamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {

        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaRepository.findAll();
        } else {
            pizzas = pizzaRepository.findByDescriptionContainingIgnoreCase(keyword.get());
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("keyword", keyword.orElse(""));
        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {

        Optional<Pizza> pizza = pizzaRepository.findById(id);

        pizza.ifPresent(value -> model.addAttribute("onePizza", value));

        return pizza.isEmpty() ? "error" : "pizza/pizza-detail";
    }
}
