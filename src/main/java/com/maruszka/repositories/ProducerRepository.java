package com.maruszka.repositories;

import com.maruszka.model.Producer;
import com.maruszka.model.enums.ProducerType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    Producer findByProducerName(String producerName);

    List<Producer> findAllByProducerNameLike(String producerName);

    Set<Producer> findByOrderByProducerNameAsc();

    Set<Producer> findProducerByProduct(ProducerType product);
}
