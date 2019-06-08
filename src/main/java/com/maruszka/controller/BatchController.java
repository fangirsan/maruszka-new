package com.maruszka.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maruszka.model.Batch;
import com.maruszka.services.BatchService;

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
