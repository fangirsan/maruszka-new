package com.maruszka.services.springdatajpa;

import com.maruszka.model.Country;
import com.maruszka.model.Hop;
import com.maruszka.model.Malt;
import com.maruszka.repositories.CountryRepository;
import com.maruszka.repositories.HopRepository;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final MaltRepository maltRepository;
    private final HopRepository hopRepository;

    public CountryServiceImpl(CountryRepository countryRepository, MaltRepository maltRepository, HopRepository hopRepository) {
        this.countryRepository = countryRepository;
        this.maltRepository = maltRepository;
        this.hopRepository = hopRepository;
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
    public Country save(Country country) {
        log.debug("Country: " + country.getCountryName() + " has been saved");
        return countryRepository.save(country);
    }

    @Override
    public void delete(Country object) {
        countryRepository.delete(object);
    }

    @Override
    public void deleteById(Long countryIdToDelete) {

        Set<Malt> malts = maltRepository.findByCountry_id(countryIdToDelete);
        if (malts.size() != 0 ) {
            for (Malt tempMalt : malts) {
                log.debug("Detaching country from malt: " + tempMalt.getName());
                tempMalt.setCountry(findByCountryName("N/A"));
            }
        }

        Set<Hop> hops = hopRepository.findByCountry_id(countryIdToDelete);
        if (hops.size() != 0) {
            for (Hop tempHop : hops) {
                log.debug("Detaching country from hop: " + tempHop.getName());
                tempHop.setCountry(findByCountryName("N/A"));
            }
        }

        Optional<Country> countryOptional = countryRepository.findById(countryIdToDelete);
        if (countryOptional.isPresent()) {
            String countryName = findById(countryIdToDelete).getCountryName();
            countryRepository.deleteById(countryIdToDelete);
            log.info("Country: " + countryName + " has been deleted." );
        }
    }

    @Override
    public Country findByCountryName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    @Override
    public Set<Country> findAllByCountryNameLike(String countryName) {
        return countryRepository.findAllByCountryNameLike(countryName);
    }

    @Override
    public Set<Country> findByOrderByCountryNameAsc() {

        Set<Country> countries = countryRepository.findByOrderByCountryNameAsc();

        // do not show N/A in the Country list
        countries.removeIf(country -> country.getCountryName().equals("N/A"));

        return countries;
    }

}
