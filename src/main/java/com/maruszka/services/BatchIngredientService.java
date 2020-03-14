package com.maruszka.services;

import com.maruszka.model.Batch;
import com.maruszka.model.Ingredient;
import com.maruszka.model.association.BatchIngredient;

import java.util.Set;

public interface BatchIngredientService extends CrudService<BatchIngredient, Long> {

    void addIngredient(Batch batch, Ingredient ingredient, int amount, String wayOfServing);

    Set<BatchIngredient> findAllByBatchId(Long batchId);
}

