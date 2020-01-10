package com.maruszka.services;

import com.maruszka.model.Malt;

import java.util.List;
import java.util.Set;

public interface MaltService extends CrudService<Malt, Long> {

    Malt findByName(String name);

    List<Malt> findAllByNameLike(String name);

    Set<Malt> findByOrderByNameAsc();

    Set<String> findAllNames();

}
