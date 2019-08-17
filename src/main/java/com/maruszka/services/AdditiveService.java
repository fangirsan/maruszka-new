package com.maruszka.services;

import com.maruszka.model.Additive;

import java.util.List;
import java.util.Set;

public interface AdditiveService extends CrudService<Additive, Long> {

    Additive findByAdditiveName(String additiveName);

    List<Additive> findAllByAdditiveNameLike(String additiveName);

    Set<Additive> findByOrderByAdditiveNameAsc();

    Set<String> findAllAdditiveNames();

}
