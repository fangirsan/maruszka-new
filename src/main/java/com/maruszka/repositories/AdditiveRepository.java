package com.maruszka.repositories;

import com.maruszka.model.Additive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface AdditiveRepository extends CrudRepository<Additive, Long> {

    Additive findByName(String name);

    List<Additive> findAllByNameLike(String name);

    Set<Additive> findByOrderByNameAsc();

}
