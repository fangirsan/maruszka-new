package com.maruszka.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByCountryName(String countryName);

    Set<Country> findAllByCountryNameLike(String countryName);

    Set<Country> findByOrderByCountryNameAsc();

    //TODO: add searching by countryCode
}
