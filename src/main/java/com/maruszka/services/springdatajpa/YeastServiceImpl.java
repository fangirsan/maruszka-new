package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maruszka.model.Batch;
import com.maruszka.repositories.BatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Yeast;
import com.maruszka.repositories.YeastRepository;
import com.maruszka.services.YeastService;

@Slf4j
@Service
public class YeastServiceImpl implements YeastService {

    private final YeastRepository yeastRepository;
    private final BatchRepository batchRepository;

    public YeastServiceImpl(YeastRepository yeastRepository, BatchRepository batchRepository) {
        this.yeastRepository = yeastRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Set<Yeast> findAll() {

        Set<Yeast> yeasts = new HashSet<>();
        yeastRepository.findAll().forEach(yeasts::add);

        return yeasts;
    }

    @Override
    public Yeast findById(Long id) {
        return yeastRepository.findById(id).orElse(null);
    }

    @Override
    public Yeast save(Yeast object) {
        return yeastRepository.save(object);
    }

    @Override
    public void delete(Yeast object) {
        yeastRepository.delete(object);
    }

    @Override
    public void deleteById(Long yeastIdToDelete) {

//        String yeastName = findById(yeastIdToDelete).toString();
//        Set<Batch> batches = batchRepository.findByYeast_id(yeastIdToDelete);
//
//        if (batches.size() != 0 ) {
//            for (Batch tempBatch : batches) {
//                log.debug("Detaching yeast: " + yeastName + " from batch number: " + tempBatch.getBatchNumber());
//                tempBatch.setYeast(yeastRepository.findByYeastName("N/A"));
//            }
//            yeastRepository.deleteById(yeastIdToDelete);
//        } else {
//            yeastRepository.deleteById(yeastIdToDelete);
//        }
    }

    @Override
    public Yeast findByYeastName(String yeastName) {
        return yeastRepository.findByYeastName(yeastName);
    }

    @Override
    public List<Yeast> findAllByYeastNameLike(String YeastName) {
        return yeastRepository.findAllByYeastNameLike(YeastName);
    }

    @Override
    public Set<Yeast> findByOrderByYeastNameAsc() {

        Set<Yeast> yeasts = yeastRepository.findByOrderByYeastNameAsc();

        // do not show N/A in the Yeast list
        yeasts.removeIf(yeast -> yeast.getYeastName().equals("N/A"));

        return yeasts;
    }

}
