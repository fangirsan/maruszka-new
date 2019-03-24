package com.maruszka.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.maruszka.model.Yeast;

public interface YeastRepository extends CrudRepository<Yeast, Long> {

	Yeast findByYeastName(String yeastName);
	
	List<Yeast> findAllByYeastNameLike(String YeastName);
	
}