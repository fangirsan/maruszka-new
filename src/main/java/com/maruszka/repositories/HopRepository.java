package com.maruszka.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Hop;

public interface HopRepository extends CrudRepository<Hop, Long> {

	Hop findByHopName(String hopName);
	
	List<Hop> findAllByHopNameLike(String hopName);
}
