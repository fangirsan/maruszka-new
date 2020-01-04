package com.maruszka.services.springdatajpa;

import com.maruszka.model.Batch;
import com.maruszka.model.MaltConversionRest;
import com.maruszka.model.association.BatchMaltConversionRest;
import com.maruszka.repositories.BatchMaltConversionRestRepository;
import com.maruszka.services.BatchMaltConversionRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Profile("springdatajpa")
public class BatchMaltConversionRestServiceImpl implements BatchMaltConversionRestService {

    private final BatchMaltConversionRestRepository batchMaltConversionRestRepository;

    public BatchMaltConversionRestServiceImpl(BatchMaltConversionRestRepository batchMaltConversionRestRepository) {
        this.batchMaltConversionRestRepository = batchMaltConversionRestRepository;
    }

    @Override
    public Set<BatchMaltConversionRest> findAll() {
        return null;
    }

    @Override
    public BatchMaltConversionRest findById(Long aLong) {
        return null;
    }

    @Override
    public BatchMaltConversionRest save(BatchMaltConversionRest object) {
        return batchMaltConversionRestRepository.save(object);
    }

    @Override
    public void delete(BatchMaltConversionRest object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void addMaltConversionRest(Batch batch, MaltConversionRest maltConversionRest, Integer minutes) {
        BatchMaltConversionRest association = new BatchMaltConversionRest();
//        association.setMaltConversionRest(maltConversionRest);
        association.setBatch(batch);
        association.setMaltConversionId(maltConversionRest.getId());
        association.setBatchId(batch.getId());
        association.setMinutes(minutes);
        batch.getRests().add(association);
//        if (maltConversionRest.getBatches() == null) {
//            //jak zainicjalizowac Set "batches" w tym momencie ?
//            maltConversionRest.setBatches(new HashSet<>());
//        }
//        maltConversionRest.getBatches().add(association);
        // TODO dodac osobny serwis i repo na maltConversionRest bo kurwa tak...
        this.save(association);

    }
}
