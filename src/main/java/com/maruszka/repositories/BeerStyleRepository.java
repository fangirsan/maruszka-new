package com.maruszka.repositories;

import java.util.Set;

import com.maruszka.model.BeerStyle;
import org.springframework.data.repository.CrudRepository;

public interface BeerStyleRepository extends CrudRepository<BeerStyle, Long> {

    BeerStyle findByBeerStyle(String beerStyle);

    Set<BeerStyle> findAllByBeerStyleLike(String beerStyle);
}
