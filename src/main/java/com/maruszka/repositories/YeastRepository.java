package com.maruszka.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Yeast;

public interface YeastRepository extends CrudRepository<Yeast, Long> {

    Yeast findByYeastName(String yeastName);

    List<Yeast> findAllByYeastNameLike(String YeastName);

    Set<Yeast> findByOrderByYeastNameAsc();

    Set<Yeast> findByProducer_id(Long producerIdToDelete);

}
