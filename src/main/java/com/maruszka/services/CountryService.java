package com.maruszka.services;

import com.maruszka.model.Country;

import java.util.Set;

public interface CountryService extends CrudService<Country, Long> {

    Country findByCountryName(String countryName);

    Set<Country> findAllByCountryNameLike(String countryName);

    Set<Country> findByOrderByCountryNameAsc();

}
