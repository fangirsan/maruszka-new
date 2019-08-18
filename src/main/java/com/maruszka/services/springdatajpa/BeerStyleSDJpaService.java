package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.maruszka.model.BeerStyle;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BeerStyleService;

@Service
@Profile("springdatajpa")
public class BeerStyleSDJpaService implements BeerStyleService {

    private final BeerStyleRepository beerStyleRepository;

    public BeerStyleSDJpaService(BeerStyleRepository beerStyleRepository) {
        this.beerStyleRepository = beerStyleRepository;
    }

    @Override
    public Set<BeerStyle> findAll() {
        Set<BeerStyle> beerStyle = new HashSet<>();
        beerStyleRepository.findAll().forEach(beerStyle::add);

        return beerStyle;
    }

    @Override
    public BeerStyle findById(Long id) {
        return beerStyleRepository.findById(id).orElse(null);
    }

    @Override
    public BeerStyle save(BeerStyle object) {
        return beerStyleRepository.save(object);
    }

    @Override
    public void delete(BeerStyle object) {
        beerStyleRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        beerStyleRepository.deleteById(id);
    }

    @Override
    public BeerStyle findByBeerType(String beerType) {
        return beerStyleRepository.findByBeerStyle(beerType);
    }

    @Override
    public Set<BeerStyle> findAllByBeerTypeLike(String beerType) {
        return findAllByBeerTypeLike(beerType);
    }

}
