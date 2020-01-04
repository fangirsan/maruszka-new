package com.maruszka.services.springdatajpa;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.Batch;
import com.maruszka.model.MaltConversionRest;
import com.maruszka.model.association.BatchMaltConversionRest;
import com.maruszka.repositories.MaltConversionRestRepository;
import com.maruszka.services.MaltConversionRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MaltConversionRestServiceImpl implements MaltConversionRestService {

    private final MaltConversionRestRepository maltConversionRestRepository;

    public MaltConversionRestServiceImpl(MaltConversionRestRepository maltConversionRestRepository) {
        this.maltConversionRestRepository = maltConversionRestRepository;
    }

    @Override
    public Set<MaltConversionRest> findAll() {

        Set<MaltConversionRest> maltConversionRests = new HashSet<>();
        maltConversionRestRepository.findAll().forEach(maltConversionRests::add);

        return maltConversionRests;
    }

    @Override
    public MaltConversionRest findById(Long id) {

        Optional<MaltConversionRest> maltConversionRestOptional = maltConversionRestRepository.findById(id);

        if (!maltConversionRestOptional.isPresent()) {
            throw new NotFoundException("Malt Conversion Rest not found. For Id value: " + id.toString());
        }

        return maltConversionRestOptional.get();
    }

    @Override
    public MaltConversionRest save(MaltConversionRest object) {
        return maltConversionRestRepository.save(object);
    }

    @Override
    public void delete(MaltConversionRest object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public MaltConversionRest findByRestName(String restName) {
        return maltConversionRestRepository.findByRestName(restName);
    }

}
