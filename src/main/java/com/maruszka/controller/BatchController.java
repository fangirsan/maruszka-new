package com.maruszka.controller;

import com.maruszka.model.*;
import com.maruszka.services.BatchService;
import com.maruszka.services.BeerStyleService;
import com.maruszka.utils.DuplicateCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/batch")
class BatchController {

    private static final String VIEWS_BATCH_CREATE_OR_UPDATE_FORM = "batch/createOrUpdateBatchForm";

    private final BatchService batchService;
    private final DuplicateCheck duplicateCheck;
    private final BeerStyleService beerStyleService;

    public BatchController(BatchService batchService, DuplicateCheck duplicateCheck, BeerStyleService beerStyleService) {
        this.batchService = batchService;
        this.duplicateCheck = duplicateCheck;
        this.beerStyleService = beerStyleService;
    }

    @ModelAttribute("beerStyles")
    public Set<BeerStyle> populateBeerStyles() {
        return beerStyleService.findByOrderByBeerStyleAsc();
    }

    @GetMapping("/{batchId}")
    public ModelAndView showBatch(@PathVariable("batchId") Long batchId) {

        ModelAndView mav = new ModelAndView("batch/batch-show");
        Batch batchToShow = batchService.findById(batchId);

        mav.addObject(batchService.findById(batchId));
        mav.addObject("malts", batchService.getBatchIngredientsByIngredient(batchToShow, Malt.class));
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

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Batch batch = new Batch();
        model.addAttribute("batch", batch);

        return VIEWS_BATCH_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{batchId}/edit")
    public String initUpdateBatchForm(@PathVariable("batchId") Long batchId, Model model) {

        model.addAttribute("batch", batchService.findById(batchId));
        return VIEWS_BATCH_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveBatch")
    public String saveOrUpdate(@RequestParam ("creationDate") LocalDateTime date, @Valid @ModelAttribute("batch") Batch batch, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_BATCH_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate(batch.getBatchNumber()) && batch.isNew()) {
//                bindingResult.rejectValue("name", "duplicate", "Duplicate name");
//                log.info("Malt with given name: [" + malt.getName() + "] already exists");
//
                return VIEWS_BATCH_CREATE_OR_UPDATE_FORM;
            } else {
                Batch savedBatch = batchService.save(batch);
                return "redirect:/batch/" + savedBatch.getId();
            }
        }
    }

}
