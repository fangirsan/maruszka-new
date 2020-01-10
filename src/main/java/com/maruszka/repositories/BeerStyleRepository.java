package com.maruszka.repositories;

import com.maruszka.model.BeerStyle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BeerStyleRepository extends CrudRepository<BeerStyle, Long> {

    BeerStyle findByBeerStyleName(String beerStyleName);

    List<BeerStyle> findAllByBeerStyleNameLike(String beerStyleName);

    Set<BeerStyle> findByOrderByBeerStyleNameAsc();

//    Set<BeerStyle> findByBatch_id(Long beerStyleToDelete);

}
