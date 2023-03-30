package org.learning.lamiapizzeria.repository;

import org.learning.lamiapizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByDescriptionContainingIgnoreCase(String keyword);
}
