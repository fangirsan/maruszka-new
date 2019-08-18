package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.BeerStyle;

public interface BeerStyleService extends CrudService<BeerStyle, Long> {

    BeerStyle findByBeerType(String beerType);

    List<BeerStyle> findAllByBeerTypeLike(String beerType);

    Set<BeerStyle> findByOrderByBeerStyleAsc();

}
