package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.maruszka.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Batch;
import com.maruszka.model.Malt;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.services.MaltService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Profile("springdatajpa")
public class MaltSDJpaService implements MaltService {

    private final MaltRepository maltRepository;
    private final BatchRepository batchRepository;

    @Autowired
    public MaltSDJpaService(MaltRepository maltRepository, BatchRepository batchRepository) {
        this.maltRepository = maltRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Set<Malt> findAll() {

        Set<Malt> malts = new HashSet<Malt>();
        maltRepository.findAll().forEach(malts::add);

        return malts;
    }

    @Override
    public Set<String> findAllMaltNames() {

        Set<String> maltNames = new HashSet<>();

        for (Malt tempMalt : maltRepository.findAll()) {
            maltNames.add(tempMalt.getMaltName().toLowerCase());
        }

        return maltNames;
    }

    @Override
    public Malt findById(Long id) {

        Optional<Malt> maltOptional = maltRepository.findById(id);

        if (!maltOptional.isPresent()) {
            throw new NotFoundException("Malt not found. For Id value: " + id.toString());
        }

        return maltOptional.get();
    }

    @Override
    public Malt save(Malt malt) {
        return maltRepository.save(malt);
    }

    @Override
    public void delete(Malt object) {
        maltRepository.delete(object);
    }

    @Override
    public void deleteById(Long maltIdToDelete) {

//        String maltName = findById(maltIdToDelete).getMaltName();
//        Set<Batch> batches = batchRepository.findByMalts_id(maltIdToDelete);
//
//        if (batches != null) {
//            for (Batch tempBatch : batches) {
//                Optional<Malt> maltOptional = tempBatch
//                        .getMalts()
//                        .stream()
//                        .filter(malt -> malt.getId().equals(maltIdToDelete))
//                        .findFirst();
//
//                if (maltOptional.isPresent()) {
//                    log.debug("Detaching malt: " + maltName + " from batch number: " + tempBatch.getBatchNumber());
//                    Malt maltToDelete = maltOptional.get();
//                    maltToDelete.setBatches(null);
//                    tempBatch.getMalts().remove(maltOptional.get());
//                    batchRepository.save(tempBatch);
//                }
//            }
//            maltRepository.deleteById(maltIdToDelete);
//            log.debug("Malt: " + maltName + " has been deleted.");
//        }
    }

    @Override
    public Malt findByMaltName(String maltName) {
        return maltRepository.findByMaltName(maltName);
    }

    @Override
    public Set<Malt> findByOrderByMaltNameAsc() {
        return maltRepository.findByOrderByMaltNameAsc();
    }

    @Override
    public List<Malt> findAllByMaltNameLike(String maltName) {
        return maltRepository.findAllByMaltNameLike(maltName);
    }

}
