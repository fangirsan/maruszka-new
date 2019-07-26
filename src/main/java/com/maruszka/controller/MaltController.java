package com.maruszka.controller;

import com.maruszka.model.Country;
import com.maruszka.model.enums.ProducerType;
import com.maruszka.model.Malt;
import com.maruszka.model.Producer;
import com.maruszka.services.CountryService;
import com.maruszka.services.MaltService;
import com.maruszka.services.ProducerService;
import com.maruszka.utils.DuplicateCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/malt")
public class MaltController {

    private static final String VIEWS_MALT_CREATE_OR_UPDATE_FORM = "malt/createOrUpdateMaltForm";

    private final MaltService maltService;
    private final CountryService countryService;
    private final ProducerService producerService;
    private final DuplicateCheck duplicateCheck;

    public MaltController(MaltService maltService, CountryService countryService, ProducerService producerService, DuplicateCheck duplicateCheck) {
        this.maltService = maltService;
        this.countryService = countryService;
        this.producerService = producerService;
        this.duplicateCheck = duplicateCheck;
    }

    // Method invoked in first place, adding "countries" to all models
    @ModelAttribute("countries")
    public Set<Country> populateCountries() {
        return countryService.findByOrderByCountryNameAsc();
    }

    @ModelAttribute("producers")
    public Set<Producer> populateProducers() { return producerService.findProducerByProduct(ProducerType.Malt); }

    @RequestMapping("/find")
    public String findMalts(Model model) {

        model.addAttribute("malt", new Malt());
        return "malt/malt-list";
    }

    @GetMapping("/find/result")
    public String processFindForm(Malt malt, BindingResult result, Model model) {

        if (malt.getMaltName() == null) {
            malt.setMaltName(""); // empty string signifies broadest possible search
        }

        // find malt by maltName
        List<Malt> results = maltService.findAllByMaltNameLike("%" + malt.getMaltName() + "%");

        if (results.isEmpty()) {
            // no result
            result.rejectValue("maltName", "notFound", "not found");
            return "malt/malt-list";
        } else if (results.size() == 1) {
            // 1 result
            malt = results.get(0);
            return "redirect:/malt/" + malt.getId();
        } else {
            // multiple results
            model.addAttribute("selections", results);
            return "malt/malt-list";
        }
    }

    @GetMapping("/{maltId}")
        public ModelAndView showMalt(@PathVariable("maltId") Long maltId) {

        ModelAndView mav = new ModelAndView("malt/malt-show");
        Malt maltToShow = maltService.findById(maltId);

        if (maltToShow.getCountry() == null) {
            maltToShow.setCountry(countryService.findByCountryName("N/A"));
        }

        mav.addObject(maltService.findById(maltId));

        return mav;
    }

    @GetMapping("/list")
    public String getMalts(Model model) {

        Set<Malt> malts = maltService.findByOrderByMaltNameAsc();
        model.addAttribute("malts", malts);
        return "malt/malt-list";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        Malt malt = new Malt();
        model.addAttribute("malt", malt);

        return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{maltId}/edit")
    public String initUpdateMaltForm(@PathVariable("maltId") Long maltId, Model model) {

        model.addAttribute("malt", maltService.findById(maltId));
        return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveMalt")
    public String saveOrUpdate(@Valid @ModelAttribute("malt") Malt malt, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("MALT_NAME", "MALT", malt.getMaltName()) && malt.isNew()) {
                bindingResult.rejectValue("maltName", "duplicate", "Duplicate name");
                log.info("Malt with given name: [" + malt.getMaltName() + "] already exists");

                return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
            } else {
                Malt savedMalt = maltService.save(malt);
                return "redirect:/malt/" + savedMalt.getId();
            }
        }
    }

    @RequestMapping("/delete/{maltId}")
    public String deleteMalt(@PathVariable("maltId") Long maltId) {

        maltService.deleteById(maltId);

        return "redirect:/malt/list";
    }

}
