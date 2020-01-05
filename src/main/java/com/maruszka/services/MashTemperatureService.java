package com.maruszka.services;

import com.maruszka.model.MashTemperature;

public interface MashTemperatureService extends CrudService<MashTemperature, Long> {

    MashTemperature findByName(String name);

}
