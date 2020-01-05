package com.maruszka.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Yeast;

public interface YeastRepository extends CrudRepository<Yeast, Long> {

    Yeast findByName(String name);

    List<Yeast> findAllByNameLike(String name);

    Set<Yeast> findByOrderByNameAsc();

    Set<Yeast> findByProducer_id(Long producerIdToDelete);

}
