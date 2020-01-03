package com.maruszka.repositories;

import java.util.Set;

import com.maruszka.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;

public interface BatchRepository extends CrudRepository<Batch, Long> {

    Batch findBatchByBeerStyle(BeerStyle beerStyle);

    Set<Batch> findAllByBeerStyleLike(BeerStyle beerStyle);

    Set<Batch> findByOrderByBatchNumberAsc();

    Set<Batch> findByBeerStyle_id(Long beerStyleToDelete);

}
