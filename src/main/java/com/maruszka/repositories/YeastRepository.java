package com.maruszka.repositories;

import com.maruszka.model.Yeast;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface YeastRepository extends CrudRepository<Yeast, Long> {

    Yeast findByName(String name);

    List<Yeast> findAllByNameLike(String name);

    Set<Yeast> findByOrderByNameAsc();

    Set<Yeast> findByProducer_id(Long producerIdToDelete);

}
