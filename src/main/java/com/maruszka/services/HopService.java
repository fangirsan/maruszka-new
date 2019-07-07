package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.Hop;

public interface HopService extends CrudService<Hop, Long> {

    Set<String> findAllHopNames();

    Hop findByHopName(String hopName);
	
	List<Hop> findAllByHopNameLike(String hopName);

    Set<Hop> findByOrderByHopNameAsc();
}
