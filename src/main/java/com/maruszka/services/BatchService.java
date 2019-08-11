package com.maruszka.services;

import java.util.Set;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerType;

public interface BatchService extends CrudService<Batch, Long> {

    Batch findBatchByBeerType(BeerType beerType);

    Set<Batch> findAllByBeerTypeLike(BeerType beerType);

    Set<Batch> findByOrderByBatchNumberAsc();

}
