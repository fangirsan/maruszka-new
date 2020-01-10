package com.maruszka.services;

import com.maruszka.model.Hop;

import java.util.List;
import java.util.Set;

public interface HopService extends CrudService<Hop, Long> {

    Set<String> findAllNames();

    Hop findByName(String hopName);
	
	List<Hop> findAllByNameLike(String hopName);

    Set<Hop> findByOrderByNameAsc();
}
