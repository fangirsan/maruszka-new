package com.maruszka.controller;

import com.maruszka.model.Additive;
import com.maruszka.services.AdditiveService;
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
@RequestMapping("/additive")
public class AdditiveController {

    private static final String VIEWS_ADDITIVE_CREATE_OR_UPDATE_FORM = "additive/createOrUpdateAdditiveForm";

    private final AdditiveService additiveService;
    private final DuplicateCheck duplicateCheck;

    public AdditiveController(AdditiveService additiveService, DuplicateCheck duplicateCheck) {
        this.additiveService = additiveService;
        this.duplicateCheck = duplicateCheck;
    }

    @GetMapping("/list")
    public String getAdditive(Model model) {

        Set<Additive> additives = additiveService.findByOrderByAdditiveNameAsc();
        model.addAttribute("additives", additives);

        return "additive/additive-list";
    }

    @RequestMapping("/find")
    public String findAdditives(Model model) {

        model.addAttribute("additive", new Additive());
        return "additive/additive-list";
    }

    @GetMapping("/{additiveId}")
    public ModelAndView showAdditive(@PathVariable("additiveId") Long additiveId) {

        ModelAndView mav = new ModelAndView("additive/additive-show");
        Additive additiveToShow = additiveService.findById(additiveId);

        mav.addObject(additiveToShow);

        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Additive additive = new Additive();
        model.addAttribute("additive", additive);

        return VIEWS_ADDITIVE_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{additiveId}/edit")
    public String initUpdateForm(@PathVariable("additiveId") Long additiveId, Model model) {

        model.addAttribute("additive", additiveService.findById(additiveId));
        return VIEWS_ADDITIVE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveAdditive")
    public String saveOrUpdate(@Valid @ModelAttribute("additive") Additive additive, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_ADDITIVE_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("ADDITIVE_NAME", "ADDITIVE", additive.getAdditiveName()) && additive.isNew()) {
                bindingResult.rejectValue("additiveName", "duplicate", "Duplicate name");
                log.info("Additive with given name: [" + additive.getAdditiveName() + "] already exists");

                return VIEWS_ADDITIVE_CREATE_OR_UPDATE_FORM;
            } else {
                Additive savedAdditive = additiveService.save(additive);
                return "redirect:/additive/" + savedAdditive.getId();
            }
        }
    }

    @RequestMapping("/delete/{additiveId}")
    public String deleteAdditive(@PathVariable("additiveId") Long additiveId) {

        additiveService.deleteById(additiveId);

        return "redirect:/additive/list";
    }
}
