package com.maruszka.services.springdatajpa;

import com.maruszka.model.MashConversionRest;
import com.maruszka.repositories.MashConversionRestRepository;
import com.maruszka.services.MashConversionRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Profile("springdatajpa")
public class MashConversionRestServiceImpl implements MashConversionRestService {

    private final MashConversionRestRepository mashConversionRestRepository;

    public MashConversionRestServiceImpl(MashConversionRestRepository mashConversionRestRepository) {
        this.mashConversionRestRepository = mashConversionRestRepository;
    }

    @Override
    public Set<MashConversionRest> findAll() {

        Set<MashConversionRest> mashConversionRests = new HashSet<>();
        mashConversionRestRepository.findAll().forEach(mashConversionRests::add);

        return mashConversionRests;
    }

    @Override
    public MashConversionRest findById(Long aLong) {
        return null;
    }

    @Override
    public MashConversionRest save(MashConversionRest object) {
        return mashConversionRestRepository.save(object);
    }

    @Override
    public void delete(MashConversionRest object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
