package com.maruszka.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerType;

public interface BatchRepository extends CrudRepository<Batch, Long> {
	
	Batch findBatchByBeerType(BeerType beerType);
	
	Set<Batch> findAllByBeerTypeLike(BeerType beerType);
	
	Set<Batch> findByOrderByBatchNumberAsc();
	
}
