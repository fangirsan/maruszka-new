package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.BeerStyle;

public interface BeerStyleService extends CrudService<BeerStyle, Long> {

    BeerStyle findByBeerStyleName(String beerType);

    List<BeerStyle> findAllByBeerStyleLike(String beerType);

    Set<BeerStyle> findByOrderByBeerStyleAsc();

}
