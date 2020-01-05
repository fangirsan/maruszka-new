package com.maruszka.services;

import com.maruszka.model.Additive;

import java.util.List;
import java.util.Set;

public interface AdditiveService extends CrudService<Additive, Long> {

    Additive findByName(String additiveName);

    List<Additive> findAllByNameLike(String additiveName);

    Set<Additive> findByOrderByNameAsc();

    Set<String> findAllNames();

}
