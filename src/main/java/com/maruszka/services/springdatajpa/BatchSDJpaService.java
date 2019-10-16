package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.maruszka.model.BeerStyle;
import com.maruszka.model.Ingredients;
import com.maruszka.model.Malt;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Batch;
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

    // https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns
//    public void addMalt(Batch batch, Malt malt, int amount) {
//        Ingredients ingredients = new Ingredients();
//        ingredients.setMalt(malt);
//        ingredients.setBatch(batch);
//        ingredients.setMaltId(malt.getId());
////        ingredients.setBatchId(this.getId());
//        ingredients.setBatchId(1L);
//        ingredients.setAmount(amount);
//        if(batch.getMalts() == null) batch.malts = new HashSet<>();
//        this.malts.add(ingredients);
//        malt.getBatches().add(ingredients);
//    }
}
