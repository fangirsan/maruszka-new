package com.maruszka.repositories;

import com.maruszka.model.Malt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface MaltRepository extends CrudRepository<Malt, Long> {

    Malt findByName(String name);

    List<Malt> findAllByNameLike(String name);

    Set<Malt> findByOrderByNameAsc();

    Set<Malt> findByCountry_id(Long countryIdToDelete);

    Set<Malt> findByProducer_id(Long producerIdToDelete);

}
