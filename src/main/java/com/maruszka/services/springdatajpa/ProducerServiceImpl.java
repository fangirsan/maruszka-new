package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.maruszka.model.Malt;
import com.maruszka.model.Yeast;
import com.maruszka.model.enums.ProducerType;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.repositories.YeastRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Producer;
import com.maruszka.repositories.ProducerRepository;
import com.maruszka.services.ProducerService;

@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;
    private final MaltRepository maltRepository;
    private final YeastRepository yeastRepository;

    public ProducerServiceImpl(ProducerRepository maltProducerRepository, MaltRepository maltRepository, YeastRepository yeastRepository) {
        this.producerRepository = maltProducerRepository;
        this.maltRepository = maltRepository;
        this.yeastRepository = yeastRepository;
    }

    @Override
    public Set<Producer> findAll() {
        Set<Producer> producers = new HashSet<>();
        producerRepository.findAll().forEach(producers::add);

        return producers;
    }

    @Override
    public Producer findById(Long id) {
        return producerRepository.findById(id).orElse(null);
    }

    @Override
    public Producer save(Producer object) {
        return producerRepository.save(object);
    }

    @Override
    public void delete(Producer object) { producerRepository.delete(object); }

    @Override
    public void deleteById(Long producerIdToDelete) {

        String producerName = findById(producerIdToDelete).getProducerName();

        Set<Malt> malts = maltRepository.findByProducer_id(producerIdToDelete);
        if (malts.size() != 0) {
            for (Malt tempMalt : malts) {
                log.debug("Detaching " + producerName + " from malt: " + tempMalt.getMaltName());
                tempMalt.setProducer(findByProducerName("N/A"));
            }
        }

        Set<Yeast> yeasts = yeastRepository.findByProducer_id(producerIdToDelete);
        if (yeasts.size() != 0) {
            for (Yeast tempYeast : yeasts) {
                log.debug("Detaching " + producerName + " from yeast: " + tempYeast.getYeastName());
                tempYeast.setProducer(findByProducerName("N/A"));
            }
        }

        Optional<Producer> producerOptional = producerRepository.findById(producerIdToDelete);
        if (producerOptional.isPresent()) {
            producerRepository.deleteById(producerIdToDelete);
            log.info("Producer " + producerName + " has been deleted.");
        }
    }

    @Override
    public Producer findByProducerName(String maltProducerName) {
        return producerRepository.findByProducerName(maltProducerName);
    }

    @Override
    public List<Producer> findAllByProducerNameLike(String maltProducerName) {
        return producerRepository.findAllByProducerNameLike(maltProducerName);
    }

    @Override
    public Set<Producer> findProducerByProduct(ProducerType product) {
        return producerRepository.findProducerByProduct(product);
    }

    @Override
    public Set<Producer> findByOrderByProducerNameAsc() {

        Set<Producer> producers = producerRepository.findByOrderByProducerNameAsc();

        // do not show N/A in the Producer list
        producers.removeIf(producer -> producer.getProducerName().equals("N/A"));

        return producers;
    }

}
