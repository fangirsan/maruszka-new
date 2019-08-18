package com.maruszka.repositories;

import java.util.List;
import java.util.Set;

import com.maruszka.model.BeerStyle;
import org.springframework.data.repository.CrudRepository;

public interface BeerStyleRepository extends CrudRepository<BeerStyle, Long> {

    BeerStyle findByBeerStyle(String beerStyle);

    List<BeerStyle> findAllByBeerStyleLike(String beerStyle);

    Set<BeerStyle> findByOrderByBeerStyleAsc();

    Set<BeerStyle> findByBatch_id(Long beerStyleToDelete);

}
