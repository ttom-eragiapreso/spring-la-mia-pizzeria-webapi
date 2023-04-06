package org.learning.lamiapizzeria.service;

import org.learning.lamiapizzeria.PizzaNotFoundException;
import org.learning.lamiapizzeria.model.Pizza;
import org.learning.lamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> getFilteredPizzas(String keyword) {
        return pizzaRepository.findByDescriptionContainingIgnoreCase(keyword);
    }

    public Pizza getPizzaById(Integer id) {

        Optional<Pizza> pizza = pizzaRepository.findById(id);

        if (pizza.isEmpty()) throw new PizzaNotFoundException("Pizza not found");

        return pizza.get();
    }

    public Pizza storePizza(Pizza formPizza) {

        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setDescription(formPizza.getDescription());
        pizzaToPersist.setPrice(formPizza.getPrice());
        pizzaToPersist.setIngredients(formPizza.getIngredients());
        return pizzaRepository.save(pizzaToPersist);
    }

    public Pizza updatePizza(Pizza formPizza, Integer id) {
        Pizza pizzaToUpdate = getPizzaById(id);
        pizzaToUpdate.setPrice(formPizza.getPrice());
        pizzaToUpdate.setDescription(formPizza.getDescription());
        pizzaToUpdate.setIngredients(formPizza.getIngredients());

        return pizzaRepository.save(pizzaToUpdate);
    }

    public void deletePizza(Integer id) {
        Pizza pizzaToDelete = getPizzaById(id);
        pizzaRepository.delete(pizzaToDelete);
    }
}
