package com.maruszka.repositories;

import com.maruszka.model.Ingredients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface IngredientsRepository extends CrudRepository<Ingredients, Long> {

}
