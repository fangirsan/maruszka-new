package com.maruszka.controller;

import com.maruszka.model.Batch;
import com.maruszka.services.BatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/batch")
class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/list")
    public String getBatches(Model theModel) {

        Set<Batch> theBatches = batchService.findByOrderByBatchNumberAsc();

        theModel.addAttribute("batches", theBatches);

        return "batch-list";
    }

}
