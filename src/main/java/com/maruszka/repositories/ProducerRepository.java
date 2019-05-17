package com.maruszka.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Producer;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

	Producer findByProducerName(String producerName);
	
	List<Producer> findAllByProducerNameLike(String producerName);

	Set<Producer> findByOrderByProducerNameAsc();
}
