package com.maruszka.services;

import java.util.List;

import com.maruszka.model.Hop;

public interface HopService extends CrudService<Hop, Long> {

	Hop findByHopName(String hopName);
	
	List<Hop> findAllByHopNameLike(String hopName);
}
