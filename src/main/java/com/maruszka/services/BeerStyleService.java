package com.maruszka.services;

import java.util.Set;

import com.maruszka.model.BeerStyle;

public interface BeerStyleService extends CrudService<BeerStyle, Long> {

    BeerStyle findByBeerType(String beerType);

    Set<BeerStyle> findAllByBeerTypeLike(String beerType);
}
