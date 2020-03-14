package com.maruszka.dao;

import com.maruszka.model.Batch;
import com.maruszka.model.BatchForm;

import java.util.Set;

public interface BatchDao {


    BatchForm composeBatchForm(Long batchId);
}
