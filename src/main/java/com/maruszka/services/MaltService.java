package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.Malt;

public interface MaltService extends CrudService<Malt, Long> {

	Malt findByMaltName(String maltName);
	
	List<Malt> findAllByMaltNameLike(String maltName);

	Set<Malt> findByOrderByMaltNameAsc();

	Set<String> findAllMaltNames();
	
}
