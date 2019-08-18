package com.maruszka.services;

import java.util.Set;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;

public interface BatchService extends CrudService<Batch, Long> {

    Batch findBatchByBeerType(BeerStyle beerStyle);

    Set<Batch> findAllByBeerTypeLike(BeerStyle beerStyle);

    Set<Batch> findByOrderByBatchNumberAsc();

}
