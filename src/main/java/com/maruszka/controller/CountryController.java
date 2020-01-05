package com.maruszka.controller;

import com.maruszka.model.Country;
import com.maruszka.services.CountryService;
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
@RequestMapping("/country")
public class CountryController {

    private static final String VIEWS_COUNTRY_CREATE_OR_UPDATE_FORM = "country/createOrUpdateCountryForm";

    private CountryService countryService;
    private DuplicateCheck duplicateCheck;

    public CountryController(CountryService countryService, DuplicateCheck duplicateCheck) {
        this.countryService = countryService;
        this.duplicateCheck = duplicateCheck;
    }

    @GetMapping("/{countryId}")
    public ModelAndView showCountry(@PathVariable("countryId") Long countryId) {

        ModelAndView mav = new ModelAndView("country/country-show");
        Country countryToShow = countryService.findById(countryId);

        mav.addObject(countryToShow);

        return mav;
    }

    @GetMapping("/list")
    public String getCountry(Model model) {

        Set<Country> countries = countryService.findByOrderByCountryNameAsc();
        model.addAttribute("countries", countries);

        return "country/country-list";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Country country = new Country();
        model.addAttribute("country", country);

        return VIEWS_COUNTRY_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{countryId}/edit")
    public String initUpdateForm(@PathVariable("countryId") Long countryId, Model model) {

        model.addAttribute("country", countryService.findById(countryId));
        return VIEWS_COUNTRY_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveCountry")
    public String saveOrUpdate(@Valid @ModelAttribute("country") Country country, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_COUNTRY_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("COUNTRY_NAME", "COUNTRY", country.getCountryName(), Country.class.getSimpleName()) && country.isNew()) {
                bindingResult.rejectValue("countryName", "duplicate", "Duplicate name");
                log.info("Country with given name: [" + country.getCountryName() + "] already exists");

                return VIEWS_COUNTRY_CREATE_OR_UPDATE_FORM;
            } else {
                Country savedCountry = countryService.save(country);
                return "redirect:/country/" + savedCountry.getId();
            }
        }
    }

    @RequestMapping("/delete/{countryId}")
    public String deleteCountry(@PathVariable("countryId") Long countryId) {

        countryService.deleteById(countryId);

        return "redirect:/country/list";
    }
}
