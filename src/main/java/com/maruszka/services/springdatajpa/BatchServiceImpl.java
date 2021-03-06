package com.maruszka.services.springdatajpa;

import java.util.*;
import java.util.stream.Collectors;

import com.maruszka.model.*;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.model.association.BatchMashTemperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BatchService;

@Slf4j
@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BeerStyleRepository beerStyleRepository;

    public BatchServiceImpl(BatchRepository batchRepository, BeerStyleRepository beerStyleRepository) {
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

//    @Override
//    public <T>Set<T> getIngredientSetByClass(Batch batch, Class<T> clazz) {
//        Set<T> ingredientSet = new HashSet<>();
//        for (BatchIngredient ing : batch.getIngredients()) {
//            if (ing.getIngredient().getClass().isAssignableFrom(clazz) ) {
//                ingredientSet.add((T) ing.getIngredient());
//            }
//        }
//      return ingredientSet;
//    }
//
//    @Override
//    public <T> Map<T, Integer> getIngredientMapByClass(Batch batch, Class<T> clazz) {
//        Map<T, Integer> ingredientMap = new HashMap<>();
//        batch.getIngredients().stream().forEach(ing -> {
//            if (ing.getIngredient().getClass().isAssignableFrom(clazz) ) {
//                ingredientMap.put((T) ing.getIngredient(), ing.getAmount());
//            }
//        });
//        return ingredientMap;
//    }

    @Override
    public <T>Set<BatchIngredient> getBatchIngredientsByIngredient(Batch batch, Class<T> clazz) {
        Set<BatchIngredient> ingredients = new HashSet<>();
        batch.getIngredients().stream().forEach(ingredient -> {
            if (ingredient.getIngredient().getClass().isAssignableFrom(clazz)) {
                ingredients.add(ingredient);
            }
        });
        return ingredients;
    }

    @Override
    public Set<BatchMashTemperature> getBatchMashTemperature(Batch batch) {
        Set<BatchMashTemperature> sortedSet = batch.getMashTemperature().stream()
                .sorted(Comparator.comparing(bmt -> bmt.getMashTemperature().getTemp1()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return sortedSet;
    }

}