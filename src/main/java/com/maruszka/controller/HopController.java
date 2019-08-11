package com.maruszka.controller;

import com.maruszka.model.Country;
import com.maruszka.model.Hop;
import com.maruszka.services.CountryService;
import com.maruszka.services.HopService;
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
@RequestMapping("/hop")
public class HopController {

    private static final String VIEWS_HOP_CREATE_OR_UPDATE_FORM = "hop/createOrUpdateHopForm";

    private HopService hopService;
    private CountryService countryService;
    private DuplicateCheck duplicateCheck;

    public HopController(HopService hopService, CountryService countryService, DuplicateCheck duplicateCheck) {
        this.hopService = hopService;
        this.countryService = countryService;
        this.duplicateCheck = duplicateCheck;
    }

    // Method invoked in first place, adding "countries" to all models
    @ModelAttribute("countries")
    public Set<Country> populateCountries() {
        return countryService.findByOrderByCountryNameAsc();
    }

    @GetMapping("/{hopId}")
    public ModelAndView showHop(@PathVariable("hopId") Long hopId) {

        ModelAndView mav = new ModelAndView("hop/hop-show");
        Hop hopToShow = hopService.findById(hopId);

        if (hopToShow.getAlphaAcidMin() == null) {
            hopToShow.setAlphaAcidMin(BigDecimal.valueOf(0L));
        }
        if (hopToShow.getAlphaAcidMax() == null) {
            hopToShow.setAlphaAcidMax(BigDecimal.valueOf(0L));
        }

        mav.addObject(hopToShow);

        return mav;
    }

    @GetMapping("/list")
    public String getHops(Model model) {

        Set<Hop> hops = hopService.findByOrderByHopNameAsc();

        for (Hop tempHop: hops) {
            if (tempHop.getCountry() == null) {
                tempHop.setCountry(countryService.findByCountryName("N/A"));
            }
        }

        model.addAttribute("hops", hops);

        return "hop/hop-list";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Hop hop = new Hop();
        model.addAttribute("hop", hop);

        return VIEWS_HOP_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{hopId}/edit")
    public String initUpdateForm(@PathVariable("hopId") Long hopId, Model model) {

        model.addAttribute("hop", hopService.findById(hopId));
        return VIEWS_HOP_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveHop")
    public String saveOrUpdate(@Valid @ModelAttribute("hop") Hop hop, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_HOP_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("HOP_NAME", "HOP", hop.getHopName()) && hop.isNew()) {
                bindingResult.rejectValue("hopName", "duplicate", "Duplicate name");
                log.info("Hop with given name: [" + hop.getHopName() + "] already exists");

                return VIEWS_HOP_CREATE_OR_UPDATE_FORM;
            } else {
                Hop savedHop = hopService.save(hop);
                return "redirect:/hop/" + savedHop.getId();
            }
        }
    }

    @RequestMapping("/delete/{hopId}")
    public String deleteHop(@PathVariable("hopId") Long hopId) {

        hopService.deleteById(hopId);

        return "redirect:/hop/list";
    }
}
