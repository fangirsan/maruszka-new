package com.maruszka.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maruszka.model.Country;
import com.maruszka.model.Malt;
import com.maruszka.model.Producer;
import com.maruszka.services.CountryService;
import com.maruszka.services.MaltService;
import com.maruszka.services.ProducerService;

@Controller
@RequestMapping("/malt")
public class MaltController {

	private static final String VIEWS_MALT_CREATE_OR_UPDATE_FORM = "malt/createOrUpdateMaltForm";
	
	private MaltService maltService;
	private CountryService countryService;
	private ProducerService producerService;

	public MaltController(MaltService maltService, CountryService countryService, ProducerService producerService) {
		this.maltService = maltService;
		this.countryService = countryService;
		this.producerService = producerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	// Method invoked in first place, adding "countries" to all models
	@ModelAttribute("countries")
	public Set<Country> populateCountries() {
		return countryService.findByOrderByCountryNameAsc();
	}
	
	@ModelAttribute("producers")
	public Set<Producer> populateProducers() {
		return producerService.findByOrderByProducerNameAsc();
	}
	
	@RequestMapping("/find")
	public String findMalts(Model model) {
		
		model.addAttribute("malt", Malt.builder().build());
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
        mav.addObject(maltService.findById(maltId));
        return mav;
    }
	
	@GetMapping("/list")
	public String getMalts(Model theModel) {
		
		Set<Malt> theMalts = maltService.findAll();
		theModel.addAttribute("malts", theMalts);
		return "malt/malt-list";
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		
		model.addAttribute("malt", Malt.builder().build());
		
		return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/new")
	public String  processCreationForm(@Valid Malt malt, BindingResult result) {
		
		if (result.hasErrors()) {
			return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
		} else {
			Malt savedMalt = maltService.save(malt);
			return "redirect:/malt/" + savedMalt.getId();
		}
	}
	
	@GetMapping("/{maltId}/edit")
	public String initUpdateMaltForm(@PathVariable("maltId") Long maltId, Model model) {
		
		model.addAttribute("malt", maltService.findById(maltId));
		return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/{maltId}/edit") //"malt" is in the request mapping for entire class
    public String processUpdateMaltForm(@Valid Malt malt, BindingResult result, @PathVariable("maltId") Long maltId) {
		
		if (result.hasErrors()) {
			return VIEWS_MALT_CREATE_OR_UPDATE_FORM;
		} else {
			malt.setId(maltId);
			Malt savedMalt = maltService.save(malt);
	    	return "redirect:/malt/" + savedMalt.getId();
		}
    }
	
}
