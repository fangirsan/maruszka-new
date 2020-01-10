package com.maruszka.services.springdatajpa;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.Hop;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.HopRepository;
import com.maruszka.services.HopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class HopServiceImpl implements HopService {

    private final HopRepository hopRepository;
    private final BatchRepository batchRepository;

    public HopServiceImpl(HopRepository hopRepository, BatchRepository batchRepository) {
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
    public Set<String> findAllNames() {

        Set<String> names = new HashSet<>();

        for (Hop tempHop : hopRepository.findAll()) {
            names.add(tempHop.getName().toLowerCase());
        }

        return names;
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

//        String hopName = findById(hopIdToDelete).getHopName();
//        Set<Batch> batches = batchRepository.findByHops_id(hopIdToDelete);
//
//        if (batches != null) {
//            for (Batch tempBatch : batches) {
//                Optional<Hop> hopOptional = tempBatch
//                        .getHops()
//                        .stream()
//                        .filter(hop -> hop.getId().equals(hopIdToDelete))
//                        .findFirst();
//
//                if (hopOptional.isPresent()) {
//                    log.debug("Detaching hop: " + hopName + " from batch number: " + tempBatch.getBatchNumber());
//                    Hop hopToDelete = hopOptional.get();
//                    hopToDelete.setBatches(null);
//                    tempBatch.getHops().remove(hopOptional.get());
//                    batchRepository.save(tempBatch);
//                }
//            }
//            hopRepository.deleteById(hopIdToDelete);
//            log.debug("Hop: " + hopName + " has been deleted.");
//        }
    }

    @Override
    public Hop findByName(String name) {
        return hopRepository.findByName(name);
    }

    @Override
    public List<Hop> findAllByNameLike(String name) {
        return hopRepository.findAllByNameLike(name);
    }

    @Override
    public Set<Hop> findByOrderByNameAsc() {
        return hopRepository.findByOrderByNameAsc();
    }
}
