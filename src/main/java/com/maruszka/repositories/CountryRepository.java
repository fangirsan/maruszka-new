package com.maruszka.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

	Country findByCountryName(String countryName);
	
	List<Country> findAllByCountryNameLike(String countryName);

	//TODO: add searching by countryCode
}
