package com.maruszka.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;

public interface BatchRepository extends CrudRepository<Batch, Long> {

    Batch findBatchByBeerStyle(BeerStyle beerStyle);

    Set<Batch> findAllByBeerStyleLike(BeerStyle beerStyle);

    Set<Batch> findByOrderByBatchNumberAsc();

    Set<Batch> findByHops_id(Long hopIdToDelete);

    Set<Batch> findByMalts_id(Long maltIdToDelete);

    Set<Batch> findByYeast_id(Long yeastIdToDelete);

    Set<Batch> findByAdditives_id(Long additiveIdToDelete);

}
