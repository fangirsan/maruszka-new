package com.maruszka.repositories;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BatchRepository extends CrudRepository<Batch, Long> {

    Batch findBatchByBeerStyle(BeerStyle beerStyle);

    Set<Batch> findAllByBeerStyleLike(BeerStyle beerStyle);

    Set<Batch> findByOrderByBatchNumberAsc();

    Set<Batch> findByBeerStyle_id(Long beerStyleToDelete);

}
