package com.maruszka.controller;

import com.maruszka.model.BeerStyle;
import com.maruszka.repositories.BeerStyleRepository;
import com.maruszka.services.BeerStyleService;
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
@RequestMapping("/beerStyle")
public class BeerStyleController {

    private static final String VIEWS_BEER_STYLE_CREATE_OR_UPDATE_FORM = "beerStyle/createOrUpdateBeerStyleForm";

    private final BeerStyleService beerStyleService;
    private final DuplicateCheck duplicateCheck;

    public BeerStyleController(BeerStyleService beerStyleService, DuplicateCheck duplicateCheck) {
        this.beerStyleService = beerStyleService;
        this.duplicateCheck = duplicateCheck;
    }

    @GetMapping("/{beerStyleId}")
    public ModelAndView showBeerStyle(@PathVariable("beerStyleId") Long beerStyleId) {

        ModelAndView mav = new ModelAndView("beerStyle/beerStyle-show");
        BeerStyle beerStyleToShow = beerStyleService.findById(beerStyleId);

        mav.addObject(beerStyleToShow);

        return mav;
    }

    @GetMapping("/list")
    public String getBeerStyles(Model model) {

        Set<BeerStyle> beerStyles = beerStyleService.findByOrderByBeerStyleAsc();
        model.addAttribute("beerStyles", beerStyles);

        return "beerStyle/beerStyle-list";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        BeerStyle beerStyle = new BeerStyle();
        model.addAttribute("beerStyle", beerStyle);

        return VIEWS_BEER_STYLE_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/{beerStyleId}/edit")
    public String initUpdateForm(@PathVariable("beerStyleId") Long beerStyleId, Model model) {

        model.addAttribute("beerStyle", beerStyleService.findById(beerStyleId));
        return VIEWS_BEER_STYLE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/saveBeerStyle")
    public String saveOrUpdate(@Valid @ModelAttribute("beerStyle") BeerStyle beerStyle, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return VIEWS_BEER_STYLE_CREATE_OR_UPDATE_FORM;
        } else {
            if (duplicateCheck.isDuplicate("BEER_STYLE", "BEER_STYLE", beerStyle.getBeerStyle()) && beerStyle.isNew()) {
                bindingResult.rejectValue("beerStyle", "duplicate", "Duplicate name");
                log.info("Beer Style with given name: [" + beerStyle.getBeerStyle() + "] already exists");

                return VIEWS_BEER_STYLE_CREATE_OR_UPDATE_FORM;
            } else {
                BeerStyle savedBeerStyle = beerStyleService.save(beerStyle);
                return "redirect:/beerStyle/" + savedBeerStyle.getId();
            }
        }
    }

    @RequestMapping("/delete/{beerStyleId}")
    public String deleteBeerStyle(@PathVariable("beerStyleId") Long beerStyleId) {

        beerStyleService.deleteById(beerStyleId);

        return "redirect:/beerStyle/list";
    }
}
