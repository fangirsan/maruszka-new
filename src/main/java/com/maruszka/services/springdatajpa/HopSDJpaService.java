package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.Batch;
import com.maruszka.repositories.BatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Hop;
import com.maruszka.repositories.HopRepository;
import com.maruszka.services.HopService;

@Slf4j
@Service
@Profile("springdatajpa")
public class HopSDJpaService implements HopService {

    private final HopRepository hopRepository;
    private final BatchRepository batchRepository;

    public HopSDJpaService(HopRepository hopRepository, BatchRepository batchRepository) {
        this.hopRepository = hopRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Set<Hop> findAll() {
        Set<Hop> hops = new HashSet<>();
        hopRepository.findAll().forEach(hops::add);

        return hops;
    }

    @Override
    public Set<String> findAllHopNames() {

        Set<String> maltNames = new HashSet<>();

        for (Hop tempHop : hopRepository.findAll()) {
            maltNames.add(tempHop.getHopName().toLowerCase());
        }

        return maltNames;
    }

    @Override
    public Hop findById(Long id) {
        Optional<Hop> hopOptional = hopRepository.findById(id);

        if (!hopOptional.isPresent()) {
            throw new NotFoundException(("Hop not found. For Id value: " + id.toString()));
        }

        return hopOptional.get();
    }

    @Override
    public Hop save(Hop object) {
        return hopRepository.save(object);
    }

    @Override
    public void delete(Hop object) {
        hopRepository.delete(object);
    }

    @Override
    public void deleteById(Long hopIdToDelete) {

        String hopName = findById(hopIdToDelete).getHopName();
        Set<Batch> batches = batchRepository.findByHops_id(hopIdToDelete);

        if (batches != null) {
            for (Batch tempBatch : batches) {
                Optional<Hop> hopOptional = tempBatch
                        .getHops()
                        .stream()
                        .filter(hop -> hop.getId().equals(hopIdToDelete))
                        .findFirst();

                if (hopOptional.isPresent()) {
                    log.debug("Detaching hop: " + hopName + " from batch number: " + tempBatch.getBatchNumber());
                    Hop hopToDelete = hopOptional.get();
                    hopToDelete.setBatches(null);
                    tempBatch.getHops().remove(hopOptional.get());
                    batchRepository.save(tempBatch);
                }
            }
            hopRepository.deleteById(hopIdToDelete);
            log.debug("Hop: " + hopName + " has been deleted.");
        }
    }

    @Override
    public Hop findByHopName(String hopName) {
        return hopRepository.findByHopName(hopName);
    }

    @Override
    public List<Hop> findAllByHopNameLike(String hopName) {
        return hopRepository.findAllByHopNameLike(hopName);
    }

    @Override
    public Set<Hop> findByOrderByHopNameAsc() {
        return hopRepository.findByOrderByHopNameAsc();
    }
}
