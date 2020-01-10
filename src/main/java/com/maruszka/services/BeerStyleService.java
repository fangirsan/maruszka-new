package com.maruszka.services;

import com.maruszka.model.BeerStyle;

import java.util.List;
import java.util.Set;

public interface BeerStyleService extends CrudService<BeerStyle, Long> {

    BeerStyle findByBeerStyleName(String beerType);

    List<BeerStyle> findAllByBeerStyleLike(String beerType);

    Set<BeerStyle> findByOrderByBeerStyleAsc();

}
