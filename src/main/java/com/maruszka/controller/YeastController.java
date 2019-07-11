package com.maruszka.controller;

import com.maruszka.model.enums.ProducerType;
import com.maruszka.model.Producer;
import com.maruszka.model.Yeast;
import com.maruszka.services.ProducerService;
import com.maruszka.services.YeastService;
import com.maruszka.utils.DuplicateCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/yeast")
public class YeastController {

    private static final String VIEWS_YEAST_CREATE_OR_UPDATE_FORM = "yeast/createOrUpdateYeastForm";

    private final YeastService yeastService;
    private final ProducerService producerService;
    private final DuplicateCheck duplicateCheck;

    public YeastController(YeastService yeastService, ProducerService producerService, DuplicateCheck duplicateCheck) {
        this.yeastService = yeastService;
        this.producerService = producerService;
        this.duplicateCheck = duplicateCheck;
    }

    @ModelAttribute("producers")
    public Set<Producer> populateProducers() { return producerService.findProducerByProduct(ProducerType.Yeast); }

    @GetMapping("/{yeastId}")
    public ModelAndView showYeast(@PathVariable("yeastId") Long yeastId) {

        ModelAndView mav = new ModelAndView("yeast/yeast-show");
        Yeast yeastToShow = yeastService.findById(yeastId);

        if (yeastToShow.getFermentationTempMin() == null) {
            yeastToShow.setFermentationTempMin(new BigDecimal(0));
        }
        if (yeastToShow.getFermentationTempMax() == null) {
            yeastToShow.setFermentationTempMax(new BigDecimal(0));
        }
        if (yeastToShow.getAlcoholTolerance() == null) {
            yeastToShow.setAlcoholTolerance(new BigDecimal(0));
        }

        mav.addObject(yeastToShow);

        return mav;
    }

    @GetMapping("/list")
    public String getYeasts(Model model) {

        Set<Yeast> yeasts = yeastService.findByOrderByYeastNameAsc();
        model.addAttribute("yeasts", yeasts);

        return "yeast/yeast-list";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Yeast yeast = new Yeast();
        model.addAttribute("yeast", yeast);

        return VIEWS_YEAST_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{yeastId}/edit")
    public String initUpdateForm(@PathVariable("yeastId") Long yeastId, Model model) {

        model.addAttribute("yeast", yeastService.findById(yeastId));
        return VIEWS_YEAST_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveYeast")
    public String saveOrUpdate(@Valid @ModelAttribute("yeast") Yeast yeast, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_YEAST_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("YEAST_NAME", "YEAST", yeast.getYeastName()) && yeast.isNew()) {
                bindingResult.rejectValue("yeastName", "duplicate", "Duplicate name");
                log.info("Yeast with given name: [" + yeast.getYeastName() + "] already exists");

                return VIEWS_YEAST_CREATE_OR_UPDATE_FORM;
            } else {
                Yeast savedYeast = yeastService.save(yeast);
                return "redirect:/yeast/" + savedYeast.getId();
            }
        }
    }

    @RequestMapping("/delete/{yeastId}")
    public String deleteYeast(@PathVariable("yeastId") Long yeastId) {

        yeastService.deleteById(yeastId);

        return "redirect:/yeast/list";
    }

}
