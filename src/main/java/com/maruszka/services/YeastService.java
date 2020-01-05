package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.Yeast;

public interface YeastService extends CrudService<Yeast, Long> {

    Yeast findByName(String name);

    List<Yeast> findAllByNameLike(String name);

    Set<Yeast> findByOrderByNameAsc();

}
