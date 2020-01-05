package com.maruszka.services;

import java.util.List;
import java.util.Set;

import com.maruszka.model.Malt;

public interface MaltService extends CrudService<Malt, Long> {

    Malt findByName(String name);

    List<Malt> findAllByNameLike(String name);

    Set<Malt> findByOrderByNameAsc();

    Set<String> findAllNames();

}
