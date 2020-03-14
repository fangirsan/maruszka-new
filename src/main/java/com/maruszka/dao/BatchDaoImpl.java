package com.maruszka.dao;

import com.maruszka.model.Batch;
import com.maruszka.model.BatchForm;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.services.BatchIngredientService;
import com.maruszka.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BatchDaoImpl implements BatchDao {

    private final BatchService batchService;
    private final BatchIngredientService batchIngredientService;

    @Autowired
    public BatchDaoImpl(BatchService batchService, BatchIngredientService batchIngredientService) {
        this.batchService = batchService;
        this.batchIngredientService = batchIngredientService;
    }

    public Batch findBatchById(Long batchId) {

        return batchService.findById(batchId);
    }

    @Override
    public BatchForm composeBatchForm(Long batchId) {

        Batch batch = batchService.findById(batchId);
        Set<BatchIngredient> allBatchIngredients = batchIngredientService.findAllByBatchId(batchId);

        BatchForm batchForm = BatchForm.builder()
                .batch(batch)
                .batchIngredient(allBatchIngredients)
                .build();

        return batchForm;
    }

}
