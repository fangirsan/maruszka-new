package com.maruszka.services.springdatajpa;

import com.maruszka.model.Batch;
import com.maruszka.model.MashTemperature;
import com.maruszka.model.association.BatchMashTemperature;
import com.maruszka.repositories.BatchMashTemperatureRepository;
import com.maruszka.services.BatchMashTemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class BatchMashTemperatureServiceImpl implements BatchMashTemperatureService {

    private final BatchMashTemperatureRepository batchMashTemperatureRepository;

    public BatchMashTemperatureServiceImpl(BatchMashTemperatureRepository batchMashTemperatureRepository) {
        this.batchMashTemperatureRepository = batchMashTemperatureRepository;
    }

    @Override
    public Set<BatchMashTemperature> findAll() {
        return null;
    }

    @Override
    public BatchMashTemperature findById(Long aLong) {
        return null;
    }

    @Override
    public BatchMashTemperature save(BatchMashTemperature object) {
        return batchMashTemperatureRepository.save(object);
    }

    @Override
    public void delete(BatchMashTemperature object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void addMashTemperature(Batch batch, MashTemperature mashTemperature, Integer minutes) {
        BatchMashTemperature association = new BatchMashTemperature();
        association.setMashTemperature(mashTemperature);
        association.setBatch(batch);
        association.setMashTemperatureId(mashTemperature.getId());
        association.setBatchId(batch.getId());
        association.setMinutes(minutes);
        batch.getMashTemperature().add(association);
        if (mashTemperature.getBatches() == null) {
            mashTemperature.setBatches(new HashSet<>());
        }
        mashTemperature.getBatches().add(association);
        /*
         I don't need bidirectional relation, so there will be no option to search for batch from perspective of
         object MashTemperature
         */
        this.save(association);

    }
}
