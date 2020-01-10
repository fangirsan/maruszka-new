package com.maruszka.repositories;

import com.maruszka.model.Hop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface HopRepository extends CrudRepository<Hop, Long> {

    Hop findByName(String name);

    List<Hop> findAllByNameLike(String name);

    Set<Hop> findByOrderByNameAsc();

    Set<Hop> findByCountry_id(Long countryIdToDelete);
}
