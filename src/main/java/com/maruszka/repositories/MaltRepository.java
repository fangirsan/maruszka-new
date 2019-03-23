package com.maruszka.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Malt;

public interface MaltRepository extends CrudRepository<Malt, Long> {

	Malt findByMaltName(String maltName);
	
	List<Malt> findAllByMaltNameLike(String maltName);
}
