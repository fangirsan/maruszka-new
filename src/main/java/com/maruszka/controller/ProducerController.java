package com.maruszka.controller;

import com.maruszka.model.Producer;
import com.maruszka.services.ProducerService;
import com.maruszka.utils.DuplicateCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/producer")
public class ProducerController {

    private static final String VIEWS_PRODUCER_CREATE_OR_UPDATE_FORM = "producer/createOrUpdateProducerForm";

    private final ProducerService producerService;
    private final DuplicateCheck duplicateCheck;

    public ProducerController(ProducerService producerService, DuplicateCheck duplicateCheck) {
        this.producerService = producerService;
        this.duplicateCheck = duplicateCheck;
    }

    @GetMapping("/{producerId}")
    public ModelAndView showProducer(@PathVariable("producerId") Long producerId) {

        ModelAndView mav = new ModelAndView("producer/producer-show");
        Producer producerToShow = producerService.findById(producerId);

        mav.addObject(producerToShow);

        return mav;
    }

    @GetMapping("/list")
    public String getProducer(Model model) {

        Set<Producer> producers = producerService.findByOrderByProducerNameAsc();
        model.addAttribute("producers", producers);

        return "producer/producer-list";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Producer producer = new Producer();
        model.addAttribute("producer", producer);

        return VIEWS_PRODUCER_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{producerId}/edit")
    public String initUpdateForm(@PathVariable("producerId") Long producerId, Model model) {

        model.addAttribute("producer", producerService.findById(producerId));
        return VIEWS_PRODUCER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveProducer")
    public String saveOrUpdate(@Valid @ModelAttribute("producer") Producer producer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_PRODUCER_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("PRODUCER_NAME", "PRODUCER", producer.getProducerName(), Producer.class.getSimpleName()) && producer.isNew()) {
                bindingResult.rejectValue("producerName", "duplicate", "Duplicate name");
                log.info("Producer with given name: [" + producer.getProducerName() + "] already exists");

                return VIEWS_PRODUCER_CREATE_OR_UPDATE_FORM;
            } else {
                Producer savedProducer = producerService.save(producer);
                return "redirect:/producer/" + savedProducer.getId();
            }
        }
    }

    @RequestMapping("/delete/{producerId}")
    public String deleteCountry(@PathVariable("producerId") Long producerId) {

        producerService.deleteById(producerId);

        return "redirect:/producer/list";
    }

}
