package com.maruszka.repositories;

import com.maruszka.model.MashTemperature;
import org.springframework.data.repository.CrudRepository;

public interface MashTemperatureRepository extends CrudRepository<MashTemperature, Long> {

    MashTemperature findByRestName(String restName);
}
