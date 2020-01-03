package com.maruszka.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.BeerType;

public interface BeerTypeRepository extends CrudRepository<BeerType, Long> {

	BeerType findByBeerType(String beerType);
	
	Set<BeerType> findAllByBeerTypeLike(String beerType);
}
