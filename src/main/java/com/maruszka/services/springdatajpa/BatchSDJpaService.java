package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerType;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.BeerTypeRepository;
import com.maruszka.services.BatchService;

@Service
@Profile("springdatajpa")
public class BatchSDJpaService implements BatchService {

	BatchRepository batchRepository;
	BeerTypeRepository beerTypeRepository;
	
	public BatchSDJpaService(BatchRepository batchRepository, BeerTypeRepository beerTypeRepository) {
		this.batchRepository = batchRepository;
		this.beerTypeRepository = beerTypeRepository;
	}

	@Override
	public Set<Batch> findAll() {
		Set<Batch> batches = new HashSet<>();
		batchRepository.findAll().forEach(batches::add);
		
		return batches;
	}

	@Override
	public Batch findById(Long id) {
		return batchRepository.findById(id).orElse(null);
	}

	@Override
	public Batch save(Batch object) {
		return batchRepository.save(object);
	}

	@Override
	public void delete(Batch object) {
		batchRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		batchRepository.deleteById(id);
	}

	@Override
	public Batch findBatchByBeerType(BeerType beerType) {
		Batch theBatch = batchRepository.findBatchByBeerType(beerType);
		if(theBatch!=null){
			Hibernate.initialize(theBatch.getHops());
		}
		
		return theBatch;
//		return batchRepository.findBatchByBeerType(beerType);
	}

	@Override
	public Set<Batch> findAllByBeerTypeLike(BeerType beerType) {
		return batchRepository.findAllByBeerTypeLike(beerType);
	}

}
