package org.learning.lamiapizzeria.service;

import org.learning.lamiapizzeria.model.Ingredient;
import org.learning.lamiapizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll(Sort.by("name"));
    }

/*    public List<Ingredient> store(List<Ingredient> ingredients){

    }*/
}
