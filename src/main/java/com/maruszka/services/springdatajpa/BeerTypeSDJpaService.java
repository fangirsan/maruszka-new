package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.BeerType;
import com.maruszka.repositories.BeerTypeRepository;
import com.maruszka.services.BeerTypeService;

@Service
@Profile("springdatajpa")
public class BeerTypeSDJpaService implements BeerTypeService {

    private final BeerTypeRepository beerTypeRepository;

    public BeerTypeSDJpaService(BeerTypeRepository beerTypeRepository) {
        this.beerTypeRepository = beerTypeRepository;
    }

    @Override
    public Set<BeerType> findAll() {
        Set<BeerType> beerType = new HashSet<>();
        beerTypeRepository.findAll().forEach(beerType::add);

        return beerType;
    }

    @Override
    public BeerType findById(Long id) {
        return beerTypeRepository.findById(id).orElse(null);
    }

    @Override
    public BeerType save(BeerType object) {
        return beerTypeRepository.save(object);
    }

    @Override
    public void delete(BeerType object) {
        beerTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        beerTypeRepository.deleteById(id);
    }

    @Override
    public BeerType findByBeerType(String beerType) {
        return beerTypeRepository.findByBeerType(beerType);
    }

    @Override
    public Set<BeerType> findAllByBeerTypeLike(String beerType) {
        return findAllByBeerTypeLike(beerType);
    }

}
