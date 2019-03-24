package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Producer;
import com.maruszka.repositories.ProducerRepository;
import com.maruszka.services.ProducerService;

@Service
@Profile("springdatajpa")
public class ProcuerSDJpaService implements ProducerService {

	ProducerRepository producerRepository;
	
	public ProcuerSDJpaService(ProducerRepository maltProducerRepository) {
		this.producerRepository = maltProducerRepository;
	}

	@Override
	public Set<Producer> findAll() {
		Set<Producer> producers = new HashSet<>();
		producerRepository.findAll().forEach(producers::add);
		
		return producers;
	}

	@Override
	public Producer findById(Long id) {
		return producerRepository.findById(id).orElse(null);
	}

	@Override
	public Producer save(Producer object) {
		return producerRepository.save(object);
	}

	@Override
	public void delete(Producer object) {
		producerRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		producerRepository.deleteById(id);
	}

	@Override
	public Producer findByProducerName(String maltProducerName) {
		return producerRepository.findByProducerName(maltProducerName);
	}

	@Override
	public List<Producer> findAllByProducerNameLike(String maltProducerName) {
		return producerRepository.findAllByProducerNameLike(maltProducerName);
	}

}