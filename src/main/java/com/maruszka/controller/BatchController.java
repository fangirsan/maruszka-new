package com.maruszka.controller;

import com.maruszka.model.*;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.services.BatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/batch")
class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/{batchId}")
    public ModelAndView showBatch(@PathVariable("batchId") Long batchId) {

        ModelAndView mav = new ModelAndView("batch/batch-show");
        Batch batchToShow = batchService.findById(batchId);

        mav.addObject(batchService.findById(batchId));
        mav.addObject("malts", batchService.getIngredientMapByClass(batchToShow, Malt.class));
        mav.addObject("hops", batchService.getBatchIngredientsByIngredient(batchToShow, Hop.class));
        mav.addObject("additives", batchService.getBatchIngredientsByIngredient(batchToShow, Additive.class));
        mav.addObject("temperatures", batchService.getBatchMashTemperature(batchToShow));
        mav.addObject("yeasts", batchService.getBatchIngredientsByIngredient(batchToShow, Yeast.class));
        return mav;
    }

    @GetMapping("/list")
    public String getBatches(Model theModel) {

        Set<Batch> theBatches = batchService.findByOrderByBatchNumberAsc();
        theModel.addAttribute("batches", theBatches);
        return "batch/batch-list";
    }

}
