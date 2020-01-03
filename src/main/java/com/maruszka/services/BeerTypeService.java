package com.maruszka.services;

import java.util.Set;

import com.maruszka.model.BeerType;

public interface BeerTypeService extends CrudService<BeerType, Long> {
	
	BeerType findByBeerType(String beerType);
	
	Set<BeerType> findAllByBeerTypeLike(String beerType);
}
