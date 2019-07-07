package com.maruszka.controller;

import com.maruszka.model.Country;
import com.maruszka.model.Hop;
import com.maruszka.services.CountryService;
import com.maruszka.services.HopService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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

    public HopController(HopService hopService, CountryService countryService) {
        this.hopService = hopService;
        this.countryService = countryService;
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
            try {
                Hop savedHop = hopService.save(hop);
                return "redirect:/hop/" + savedHop.getId();
            }  catch (Exception e) {
                if (e instanceof ConstraintViolationException || e instanceof DataIntegrityViolationException) {
                    if (hopService.findAllHopNames().contains(hop.getHopName())) {
                        bindingResult.rejectValue("hopName", "duplicate", "Duplicate name");
                        log.info("Hop with given name: [" + hop.getHopName() + "] already exists");
                    }
                    return VIEWS_HOP_CREATE_OR_UPDATE_FORM;
                }
                return null;
            }
        }
    }

    @RequestMapping("/delete/{hopId}")
    public String deleteHop(@PathVariable("hopId") Long hopId) {

        hopService.deleteById(hopId);

        return "redirect:/hop/list";
    }
}
