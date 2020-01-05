package com.maruszka.services;

import com.maruszka.model.Batch;
import com.maruszka.model.MashTemperature;
import com.maruszka.model.association.BatchMashTemperature;

public interface BatchMashTemperatureService extends CrudService<BatchMashTemperature, Long> {

    void addMashTemperature(Batch batch, MashTemperature mashTemperature, Integer minutes);

}
