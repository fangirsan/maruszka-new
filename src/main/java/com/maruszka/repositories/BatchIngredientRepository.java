package com.maruszka.repositories;

import com.maruszka.model.association.BatchIngredient;
import org.springframework.data.repository.CrudRepository;

public interface BatchIngredientRepository extends CrudRepository<BatchIngredient, Long> {
}
