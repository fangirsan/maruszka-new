package com.maruszka.services.springdatajpa;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerStyle;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BeerStyleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class BeerStyleServiceImpl implements BeerStyleService {

    private final BeerStyleRepository beerStyleRepository;
    private final BatchRepository batchRepository;

    public BeerStyleServiceImpl(BeerStyleRepository beerStyleRepository, BatchRepository batchRepository) {
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

        String beerStyleName = findById(beerStyleToDelete).getBeerStyleName();
        Set<Batch> batches = batchRepository.findByBeerStyle_id(beerStyleToDelete);

        if (batches.size() != 0) {
            for (Batch tempBatch : batches) {
                log.debug("Detaching beer style: " + beerStyleName + " from batch number: " + tempBatch.getBatchNumber());
                tempBatch.setBeerStyle(beerStyleRepository.findByBeerStyleName("N/A"));
            }
//            beerStyleRepository.deleteById(beerStyleToDelete);
//        } else {
//            beerStyleRepository.deleteById(beerStyleToDelete);
        }
        beerStyleRepository.deleteById(beerStyleToDelete);
        log.debug("Beer style: " + beerStyleName + " has been deleted.");
    }

    @Override
    public BeerStyle findByBeerStyleName(String StyleName) {
        return beerStyleRepository.findByBeerStyleName(StyleName);
    }

    @Override
    public List<BeerStyle> findAllByBeerStyleLike(String StyleName) {
        return findAllByBeerStyleLike(StyleName);
    }

    @Override
    public Set<BeerStyle> findByOrderByBeerStyleAsc() {

        Set<BeerStyle> beerStyles = beerStyleRepository.findByOrderByBeerStyleNameAsc();

        // do not show N/A in the Beer Style list
        beerStyles.removeIf(beerStyle -> beerStyle.getBeerStyleName().equals("N/A"));

        return beerStyles;
    }

}
