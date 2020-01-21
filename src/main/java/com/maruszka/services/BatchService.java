package com.maruszka.services;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.model.association.BatchMashTemperature;

import java.math.BigDecimal;
import java.util.Set;

public interface BatchService extends CrudService<Batch, Long> {

    Batch findBatchByBeerType(BeerStyle beerStyle);

    Set<Batch> findAllByBeerTypeLike(BeerStyle beerStyle);

    Set<Batch> findByOrderByBatchNumberAsc();

//    <T>Set<T> getIngredientSetByClass(Batch batch, Class<T> clazz);
//
//    <T> Map<T, Integer> getIngredientMapByClass(Batch batch, Class<T> clazz);

    <T>Set<BatchIngredient> getBatchIngredientsByIngredient(Batch batch, Class<T> clazz);

    Set<BatchMashTemperature> getBatchMashTemperature(Batch batch);

    BigDecimal calculateEfficiency(Batch batch);
}
