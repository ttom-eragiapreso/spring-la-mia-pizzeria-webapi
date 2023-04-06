package org.learning.lamiapizzeria.controller;

import org.learning.lamiapizzeria.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        return "ingredients/index";
    }
}
