package com.maruszka.services;

import com.maruszka.model.Yeast;

import java.util.List;
import java.util.Set;

public interface YeastService extends CrudService<Yeast, Long> {

    Yeast findByName(String name);

    List<Yeast> findAllByNameLike(String name);

    Set<Yeast> findByOrderByNameAsc();

}
