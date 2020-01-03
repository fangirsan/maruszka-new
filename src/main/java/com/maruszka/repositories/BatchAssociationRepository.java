package com.maruszka.repositories;


import com.maruszka.model.BatchIngredientAssociation;
import org.springframework.data.repository.CrudRepository;

public interface BatchAssociationRepository extends CrudRepository<BatchIngredientAssociation, Long> {
}
