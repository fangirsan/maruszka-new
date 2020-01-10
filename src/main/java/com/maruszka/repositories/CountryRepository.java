package com.maruszka.repositories;

import com.maruszka.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByCountryName(String countryName);

    Set<Country> findAllByCountryNameLike(String countryName);

    Set<Country> findByOrderByCountryNameAsc();

}
