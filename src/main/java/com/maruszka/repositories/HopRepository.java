package com.maruszka.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Hop;

public interface HopRepository extends CrudRepository<Hop, Long> {

    Hop findByHopName(String hopName);

    List<Hop> findAllByHopNameLike(String hopName);

    Set<Hop> findByOrderByHopNameAsc();

    Set<Hop> findByCountry_id(Long countryIdToDelete);
}
