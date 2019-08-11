package com.maruszka.services;

import java.util.Set;

import com.maruszka.model.Country;

public interface CountryService extends CrudService<Country, Long> {

    Country findByCountryName(String countryName);

    Set<Country> findAllByCountryNameLike(String countryName);

    Set<Country> findByOrderByCountryNameAsc();

}
