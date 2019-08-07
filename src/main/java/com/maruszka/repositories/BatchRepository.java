package com.maruszka.repositories;

import java.util.Set;

import com.maruszka.model.Yeast;
import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerType;

public interface BatchRepository extends CrudRepository<Batch, Long> {
	
	Batch findBatchByBeerType(BeerType beerType);
	
	Set<Batch> findAllByBeerTypeLike(BeerType beerType);
	
	Set<Batch> findByOrderByBatchNumberAsc();
	
    Set<Batch> findByHops_id(Long hopIdToDelete);

	Set<Batch> findByMalts_id(Long maltIdToDelete);

	Set<Batch> findByYeast_id(Long yeastIdToDelete);
}
