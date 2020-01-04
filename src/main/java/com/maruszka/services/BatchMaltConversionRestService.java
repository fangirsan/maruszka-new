package com.maruszka.services;

import com.maruszka.model.Batch;
import com.maruszka.model.MaltConversionRest;
import com.maruszka.model.association.BatchMaltConversionRest;

public interface BatchMaltConversionRestService extends CrudService<BatchMaltConversionRest, Long> {

    void addMaltConversionRest(Batch batch, MaltConversionRest maltConversionRest, Integer minutes);

}
