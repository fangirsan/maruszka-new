package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.maruszka.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BatchService;

@Service
@Profile("springdatajpa")
public class BatchSDJpaService implements BatchService {

    private final BatchRepository batchRepository;
    private final BeerStyleRepository beerStyleRepository;

    public BatchSDJpaService(BatchRepository batchRepository, BeerStyleRepository beerStyleRepository) {
        this.batchRepository = batchRepository;
        this.beerStyleRepository = beerStyleRepository;
    }

    @Override
    public Set<Batch> findAll() {
        Set<Batch> batches = new HashSet<>();
        batchRepository.findAll().forEach(batches::add);

        return batches;
    }

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElse(null);
    }

    @Override
    public Batch save(Batch object) {
        return batchRepository.save(object);
    }

    @Override
    public void delete(Batch object) {
        batchRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        batchRepository.deleteById(id);
    }

    @Override
    public Batch findBatchByBeerType(BeerStyle beerStyle) {
        Batch theBatch = batchRepository.findBatchByBeerStyle(beerStyle);
//        if(theBatch!=null){
//            Hibernate.initialize(theBatch.getHops());
//        }

        return theBatch;
//		return batchRepository.findBatchByBeerType(beerType);
    }

    @Override
    public Set<Batch> findAllByBeerTypeLike(BeerStyle beerStyle) {
        return batchRepository.findAllByBeerStyleLike(beerStyle);
    }

    @Override
    public Set<Batch> findByOrderByBatchNumberAsc() {
        return batchRepository.findByOrderByBatchNumberAsc();
    }

    @Override
    public <T>Set<T> getIngredientByClass(Batch batch, Class<T> clazz) {
        Set<T> ingredientSet = new HashSet<>();
        for (BatchAssociation ing : batch.getIngredients()) {
            if (ing.getIngredient().getClass().isAssignableFrom(clazz) ) {
                ingredientSet.add((T) ing.getIngredient());
            }
        }
      return (Set<T>) ingredientSet;
    }

}
