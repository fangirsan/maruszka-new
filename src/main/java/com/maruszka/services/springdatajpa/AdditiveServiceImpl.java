package com.maruszka.services.springdatajpa;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.Additive;
import com.maruszka.repositories.AdditiveRepository;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.services.AdditiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AdditiveServiceImpl implements AdditiveService {

    private final AdditiveRepository additiveRepository;
    private final BatchRepository batchRepository;

    public AdditiveServiceImpl(AdditiveRepository additiveRepository, BatchRepository batchRepository) {
        this.additiveRepository = additiveRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Set<Additive> findAll() {

        Set<Additive> additives = new HashSet<>();
        additiveRepository.findAll().forEach(additives::add);

        return additives;
    }

    @Override
    public Set<String> findAllNames() {

        Set<String> names = new HashSet<>();

        for (Additive tempAdditive : additiveRepository.findAll()) {
            names.add(tempAdditive.getName().toLowerCase());
        }

        return names;
    }

    @Override
    public Additive findById(Long id) {

        Optional<Additive> additiveOptional = additiveRepository.findById(id);

        if (!additiveOptional.isPresent()) {
            throw new NotFoundException("Additive not found. For Id value: " + id.toString());
        }

        return additiveOptional.get();
    }

    @Override
    public Additive save(Additive additive) { return additiveRepository.save(additive); }

    @Override
    public void delete(Additive additive) { additiveRepository.delete(additive); }

    @Override
    public void deleteById(Long additiveIdToDelete) {

//        String additiveName = findById(additiveIdToDelete).getAdditiveName();
//        Set<Batch> batches = batchRepository.findByAdditives_id(additiveIdToDelete);
//
//        if (batches != null) {
//            for (Batch tempBatch : batches) {
//                Optional<Additive> additiveOptional = tempBatch
//                        .getAdditives()
//                        .stream()
//                        .filter(additive -> additive.getId().equals(additiveIdToDelete))
//                        .findFirst();
//
//                if (additiveOptional.isPresent()) {
//                    log.debug("Deleting additive: " + additiveName + " from batch number: " + tempBatch.getBatchNumber());
//                    Additive additiveToDelete = additiveOptional.get();
//                    additiveToDelete.setBatches(null);
//                    tempBatch.getAdditives().remove(additiveOptional.get());
//                    batchRepository.save(tempBatch);
//                }
//            }
//            additiveRepository.deleteById(additiveIdToDelete);
//            log.debug("Additive: " + additiveName + " has been deleted.");
//        }
    }

    @Override
    public Additive findByName(String name) {
        return additiveRepository.findByName(name);
    }

    @Override
    public Set<Additive> findByOrderByNameAsc() {
        return additiveRepository.findByOrderByNameAsc();
    }

    @Override
    public List<Additive> findAllByNameLike(String name) {
        return additiveRepository.findAllByNameLike(name);
    }
}
