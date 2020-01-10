package com.maruszka.services.springdatajpa;

import com.maruszka.model.Batch;
import com.maruszka.model.Ingredient;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.repositories.BatchIngredientRepository;
import com.maruszka.services.BatchIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class BatchIngredientServiceImpl implements BatchIngredientService {

    private final BatchIngredientRepository batchIngredientRepository;

    public BatchIngredientServiceImpl(BatchIngredientRepository batchIngredientRepository) {
        this.batchIngredientRepository = batchIngredientRepository;
    }

    @Override
    public Set<BatchIngredient> findAll() {
        return null;
    }

    @Override
    public BatchIngredient findById(Long aLong) {
        return null;
    }

    @Override
    public BatchIngredient save(BatchIngredient object) {
        return batchIngredientRepository.save(object);
    }

    @Override
    public void delete(BatchIngredient object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    // https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns
    @Override
    public void addIngredient(Batch batch, Ingredient ingredient, int amount, String wayOfServing) {
        BatchIngredient association = new BatchIngredient();
        association.setIngredient(ingredient);
        association.setBatch(batch);
        association.setIngredientId(ingredient.getId());
        association.setBatchId(batch.getId());
        association.setAmount(amount);
        association.setWayOfServing(wayOfServing);
        batch.getIngredients().add(association);
        ingredient.getBatches().add(association);
        this.save(association);
    }

}
