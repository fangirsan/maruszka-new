package com.maruszka.services;

import com.maruszka.model.Batch;
import com.maruszka.model.BatchAssociation;
import com.maruszka.model.Ingredient;

public interface BatchAssociationService extends CrudService<BatchAssociation, Long> {

    void addIngredient(Batch batch, Ingredient ingredient, int amount, String wayOfServing);

}
