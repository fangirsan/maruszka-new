package com.maruszka.repositories;

import com.maruszka.model.association.BatchIngredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BatchIngredientRepository extends CrudRepository<BatchIngredient, Long> {

    Set<BatchIngredient> findAllByBatchId(Long batchId);

}
