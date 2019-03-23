package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Hop;
import com.maruszka.repositories.HopRepository;
import com.maruszka.services.HopService;

@Service
@Profile("springdatajpa")
public class HopSDJpaService implements HopService {

	HopRepository hopRepository;
	
	public HopSDJpaService(HopRepository hopRepository) {
		this.hopRepository = hopRepository;
	}

	@Override
	public Set<Hop> findAll() {
		Set<Hop> hops = new HashSet<>();
		hopRepository.findAll().forEach(hops::add);
		
		return hops;
	}

	@Override
	public Hop findById(Long id) {
		return hopRepository.findById(id).orElse(null);
	}

	@Override
	public Hop save(Hop object) {
		return hopRepository.save(object);
	}

	@Override
	public void delete(Hop object) {
		hopRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		hopRepository.deleteById(id);
	}

	@Override
	public Hop findByHopName(String hopName) {
		return hopRepository.findByHopName(hopName);
	}

	@Override
	public List<Hop> findAllByHopNameLike(String hopName) {
		return hopRepository.findAllByHopNameLike(hopName);
	}

}
