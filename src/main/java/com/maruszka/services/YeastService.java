package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.Yeast;

public interface YeastService extends CrudService<Yeast, Long> {

    Yeast findByYeastName(String yeastName);

    List<Yeast> findAllByYeastNameLike(String YeastName);

    Set<Yeast> findByOrderByYeastNameAsc();
}
