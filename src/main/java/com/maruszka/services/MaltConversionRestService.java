package com.maruszka.services;

import com.maruszka.model.Batch;
import com.maruszka.model.MaltConversionRest;
import com.maruszka.model.association.BatchMaltConversionRest;

public interface MaltConversionRestService extends CrudService<MaltConversionRest, Long> {

    MaltConversionRest findByRestName(String restName);

}
