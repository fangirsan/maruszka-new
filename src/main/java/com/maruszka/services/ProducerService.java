package com.maruszka.services;

import java.util.List;

import com.maruszka.model.Producer;

public interface ProducerService extends CrudService<Producer, Long> {

	Producer findByProducerName(String producerName);
		
	List<Producer> findAllByProducerNameLike(String producerName);
	
	// TODO: refactor to be a generic producer
}
