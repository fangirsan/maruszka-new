package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;
import com.maruszka.repositories.BatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BeerStyleService;

@Slf4j
@Service
@Profile("springdatajpa")
public class BeerStyleSDJpaService implements BeerStyleService {

    private final BeerStyleRepository beerStyleRepository;
    private final BatchRepository batchRepository;

    public BeerStyleSDJpaService(BeerStyleRepository beerStyleRepository, BatchRepository batchRepository) {
        this.beerStyleRepository = beerStyleRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Set<BeerStyle> findAll() {

        Set<BeerStyle> beerStyle = new HashSet<>();
        beerStyleRepository.findAll().forEach(beerStyle::add);

        return beerStyle;
    }

    @Override
    public BeerStyle findById(Long id) {
        return beerStyleRepository.findById(id).orElse(null);
    }

    @Override
    public BeerStyle save(BeerStyle object) {
        return beerStyleRepository.save(object);
    }

    @Override
    public void delete(BeerStyle object) {
        beerStyleRepository.delete(object);
    }

    @Override
    public void deleteById(Long beerStyleToDelete) {

        String beerStyle = findById(beerStyleToDelete).getBeerStyle();
        Set<Batch> batches = batchRepository.findByBeerStyle_id(beerStyleToDelete);

        if (batches.size() != 0) {
            for (Batch tempBatch : batches) {
                log.debug("Detaching beer style: " + beerStyle + " from batch number: " + tempBatch.getBatchNumber());
                tempBatch.setBeerStyle(beerStyleRepository.findByBeerStyle("N/A"));
            }
//            beerStyleRepository.deleteById(beerStyleToDelete);
//        } else {
//            beerStyleRepository.deleteById(beerStyleToDelete);
        }
        beerStyleRepository.deleteById(beerStyleToDelete);
        log.debug("Beer style: " + beerStyle + " has been deleted.");
    }

    @Override
    public BeerStyle findByBeerType(String beerType) {
        return beerStyleRepository.findByBeerStyle(beerType);
    }

    @Override
    public List<BeerStyle> findAllByBeerTypeLike(String beerType) {
        return findAllByBeerTypeLike(beerType);
    }

    @Override
    public Set<BeerStyle> findByOrderByBeerStyleAsc() {

        Set<BeerStyle> beerStyles = beerStyleRepository.findByOrderByBeerStyleAsc();

        // do not show N/A in the Beer Style list
        beerStyles.removeIf(beerStyle -> beerStyle.getBeerStyle().equals("N/A"));

        return beerStyles;
    }

}
