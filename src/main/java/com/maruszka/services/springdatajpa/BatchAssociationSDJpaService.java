package com.maruszka.services.springdatajpa;

import com.maruszka.model.Batch;
import com.maruszka.model.BatchAssociation;
import com.maruszka.model.Ingredient;
import com.maruszka.repositories.BatchAssociationRepository;
import com.maruszka.services.BatchAssociationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springdatajpa")
public class BatchAssociationSDJpaService implements BatchAssociationService {

    private final BatchAssociationRepository batchAssociationRepository;

    public BatchAssociationSDJpaService(BatchAssociationRepository batchAssociationRepository) {
        this.batchAssociationRepository = batchAssociationRepository;
    }

    @Override
    public Set<BatchAssociation> findAll() {
        return null;
    }

    @Override
    public BatchAssociation findById(Long aLong) {
        return null;
    }

    @Override
    public BatchAssociation save(BatchAssociation object) {
        return batchAssociationRepository.save(object);
    }

    @Override
    public void delete(BatchAssociation object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    // https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns
    @Override
    public void addIngredient(Batch batch, Ingredient ingredient, int amount, String wayOfServing) {
        BatchAssociation association = new BatchAssociation();
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
