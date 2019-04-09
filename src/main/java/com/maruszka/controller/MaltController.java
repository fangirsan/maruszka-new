package com.maruszka.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maruszka.model.Country;
import com.maruszka.model.Malt;
import com.maruszka.services.CountryService;
import com.maruszka.services.MaltService;

@Controller
@RequestMapping("/malt")
public class MaltController {

	private MaltService maltService;
	private CountryService countryService;

	public MaltController(MaltService maltService, CountryService countryService) {
		this.maltService = maltService;
		this.countryService = countryService;
	}
	
	// Method invoked in first place, adding "countries" to all models
	@ModelAttribute("countries")
	public Collection<Country> populateCountries() {
		return countryService.findAll();
	}
	
	@GetMapping("/list")
	public String getMalts(Model theModel) {
		
		Set<Malt> theMalts = maltService.findAll();
		
		theModel.addAttribute("malts", theMalts);
		
		return "malt-list";
	}
	
	@GetMapping("/{id}")
    public ModelAndView showMalt(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("malt-show");
        mav.addObject(maltService.findById(id));
        return mav;
    }
		
	@RequestMapping("/{id}/malt-show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("malt", maltService.findById(new Long(id)));

        return "malt-show";
    }
	
	@RequestMapping("{id}/update")
	public String updateMalt(@PathVariable String id, Model model) {
		
		model.addAttribute("malt", maltService.findById(Long.valueOf(id)));
		
//		Set<Country> countries = countryService.findAll();
//		model.addAttribute("countries", countries);
		
		return "malt-form";
	}
	
	@PostMapping //"malt" is in the request mapping for entire class
    public String saveOrUpdate(@ModelAttribute Malt malt) {
		Malt savedMalt = maltService.save(malt);
    	
    	return "redirect:/malt/" + savedMalt.getId() + "/malt-show";
    }
	
}
