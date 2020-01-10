package com.maruszka.services;

import com.maruszka.model.Producer;
import com.maruszka.model.enums.ProducerType;

import java.util.List;
import java.util.Set;

public interface ProducerService extends CrudService<Producer, Long> {

    Producer findByProducerName(String producerName);

    List<Producer> findAllByProducerNameLike(String producerName);

    Set<Producer> findByOrderByProducerNameAsc();

    Set<Producer> findProducerByProduct(ProducerType product);

}
