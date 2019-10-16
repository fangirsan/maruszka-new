package com.maruszka.services.springdatajpa;

import com.maruszka.model.Ingredients;
import com.maruszka.repositories.IngredientsRepository;
import com.maruszka.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class IngredientsSDJpaService implements IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsSDJpaService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Set<Ingredients> findAll() {
        return null;
    }

    @Override
    public Ingredients findById(Long aLong) {
        return null;
    }

    @Override
    public Ingredients save(Ingredients ingredients) {
        return ingredientsRepository.save(ingredients);
    }

    @Override
    public void delete(Ingredients object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
