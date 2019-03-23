package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Country;
import com.maruszka.repositories.CountryRepository;
import com.maruszka.services.CountryService;

@Service
@Profile("springdatajpa")
public class CountrySDJpaService implements CountryService {

	private final CountryRepository countryRepository;
	
	public CountrySDJpaService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public Set<Country> findAll() {
		Set<Country> countries = new HashSet<>();
		countryRepository.findAll().forEach(countries::add);
		
		return countries;
	}

	@Override
	public Country findById(Long id) {
		return countryRepository.findById(id).orElse(null);
	}

	@Override
	public Country save(Country object) {
		return countryRepository.save(object);
	}

	@Override
	public void delete(Country object) {
		countryRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		countryRepository.deleteById(id);
	}

	@Override
	public Country findByCountryName(String countryName) {
		return countryRepository.findByCountryName(countryName);
	}

	@Override
	public List<Country> findAllByCountryNameLike(String countryName) {
		return countryRepository.findAllByCountryNameLike(countryName);
	}

}
