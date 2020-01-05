package com.maruszka.services.springdatajpa;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.MashTemperature;
import com.maruszka.repositories.MashTemperatureRepository;
import com.maruszka.services.MashTemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MashTemperatureServiceImpl implements MashTemperatureService {

    private final MashTemperatureRepository mashTemperatureRepository;

    public MashTemperatureServiceImpl(MashTemperatureRepository mashTemperatureRepository) {
        this.mashTemperatureRepository = mashTemperatureRepository;
    }

    @Override
    public Set<MashTemperature> findAll() {

        Set<MashTemperature> mashTemperatures = new HashSet<>();
        mashTemperatureRepository.findAll().forEach(mashTemperatures::add);

        return mashTemperatures;
    }

    @Override
    public MashTemperature findById(Long id) {

        Optional<MashTemperature> maltConversionRestOptional = mashTemperatureRepository.findById(id);

        if (!maltConversionRestOptional.isPresent()) {
            throw new NotFoundException("Malt Conversion Rest not found. For Id value: " + id.toString());
        }

        return maltConversionRestOptional.get();
    }

    @Override
    public MashTemperature save(MashTemperature object) {
        return mashTemperatureRepository.save(object);
    }

    @Override
    public void delete(MashTemperature object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public MashTemperature findByName(String name) {
        return mashTemperatureRepository.findByRestName(name);
    }

}
