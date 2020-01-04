package com.maruszka.repositories;

import com.maruszka.model.MaltConversionRest;
import com.maruszka.model.association.BatchMaltConversionRest;
import org.springframework.data.repository.CrudRepository;

public interface MaltConversionRestRepository extends CrudRepository<MaltConversionRest, Long> {

    MaltConversionRest findByRestName(String restName);
}
