package com.maruszka.repositories;

import java.util.List;
import java.util.Set;

import com.maruszka.model.BeerStyle;
import org.springframework.data.repository.CrudRepository;

public interface BeerStyleRepository extends CrudRepository<BeerStyle, Long> {

    BeerStyle findByBeerStyleName(String beerStyleName);

    List<BeerStyle> findAllByBeerStyleNameLike(String beerStyleName);

    Set<BeerStyle> findByOrderByBeerStyleNameAsc();

//    Set<BeerStyle> findByBatch_id(Long beerStyleToDelete);

}
