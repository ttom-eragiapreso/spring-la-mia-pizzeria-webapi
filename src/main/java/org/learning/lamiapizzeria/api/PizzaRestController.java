package org.learning.lamiapizzeria.api;

import jakarta.validation.Valid;
import org.learning.lamiapizzeria.PizzaNotFoundException;
import org.learning.lamiapizzeria.model.Pizza;
import org.learning.lamiapizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizza")
@CrossOrigin
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam(name = "q") Optional<String> search) {
        if (search.isPresent()) return pizzaService.getFilteredPizzas(search.get());
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public Pizza getPizzaById(@PathVariable Integer id) {
        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza bodyPizza) {
        return pizzaService.storePizza(bodyPizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            pizzaService.deletePizza(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public Pizza update(@Valid @RequestBody Pizza bodyPizza, @PathVariable Integer id) {

        try {
            return pizzaService.updatePizza(bodyPizza, id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
