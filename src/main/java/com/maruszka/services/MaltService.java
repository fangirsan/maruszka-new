package com.maruszka.services;

import java.util.List;

import com.maruszka.model.Malt;

public interface MaltService extends CrudService<Malt, Long> {

	Malt findByMaltName(String maltName);
	
	List<Malt> findAllByMaltNameLike(String maltName);
	
}
