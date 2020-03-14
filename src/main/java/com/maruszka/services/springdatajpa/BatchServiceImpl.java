package com.maruszka.services.springdatajpa;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;
import com.maruszka.model.Hop;
import com.maruszka.model.Malt;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.model.association.BatchMashTemperature;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BeerStyleRepository beerStyleRepository;
    private static final BigDecimal efficiencyMultiplier = new BigDecimal(1.05);
    private static final BigDecimal zero = BigDecimal.ZERO;

    @Autowired
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

    // TODO: this should return SET
    @Override
    public Batch findBatchByBeerType(BeerStyle beerStyle) {

        Batch theBatch = batchRepository.findBatchByBeerStyle(beerStyle);

        return theBatch;
    }

    @Override
    public Set<Batch> findAllByBeerTypeLike(BeerStyle beerStyle) {
        return batchRepository.findAllByBeerStyleLike(beerStyle);
    }

    @Override
    @Transactional
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

    @Override
    public BigDecimal calculateEfficiency(Batch batch) {
//        (ilość litrów brzeczki * jej Blg *1.05)/(ile kg zasypu)
        BigDecimal efficiency = null;
        Set<Integer> malts = new HashSet<>();

        Integer maltAmount = 0;
        if (batch.getIngredients() != null ) {
            batch.getIngredients().stream().forEach((malt -> {
                if (malt.getIngredient().getClass().isAssignableFrom(Malt.class)) {
                    malts.add(malt.getAmount());
                }
            }));
            maltAmount = malts.stream()
                    .reduce(0, Integer::sum);
        }

        if (batch.getVolume() != zero || batch.getBlg1() != zero || maltAmount != 0) {
            efficiency = (batch.getVolume()
                    .multiply(batch.getBlg1())
                    .multiply(efficiencyMultiplier))
                    .divide(BigDecimal.valueOf(maltAmount)
                            .divide(BigDecimal.valueOf(1000)));
        }
        return efficiency;
    }

}