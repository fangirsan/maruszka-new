package com.maruszka.services;

import java.util.List;

import com.maruszka.model.Country;

public interface CountryService extends CrudService<Country, Long> {

	Country findByCountryName(String countryName);
	
	List<Country> findAllByCountryNameLike(String countryName);
	
}
