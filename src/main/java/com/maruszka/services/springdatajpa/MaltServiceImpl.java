package com.maruszka.services.springdatajpa;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.Malt;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.services.MaltService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MaltServiceImpl implements MaltService {

    private final MaltRepository maltRepository;
    private final BatchRepository batchRepository;

    @Autowired
    public MaltServiceImpl(MaltRepository maltRepository, BatchRepository batchRepository) {
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
    public Set<String> findAllNames() {

        Set<String> name = new HashSet<>();

        for (Malt tempMalt : maltRepository.findAll()) {
            name.add(tempMalt.getName().toLowerCase());
        }

        return name;
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
    public Malt findByName(String name) {
        return maltRepository.findByName(name);
    }

    @Override
    public Set<Malt> findByOrderByNameAsc() {
        return maltRepository.findByOrderByNameAsc();
    }

    @Override
    public List<Malt> findAllByNameLike(String name) {
        return maltRepository.findAllByNameLike(name);
    }

}
