package com.maruszka.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maruszka.model.Malt;
import com.maruszka.services.MaltService;

@Controller
@RequestMapping("/malt")
public class MaltController {

	private MaltService maltService;

	public MaltController(MaltService maltService) {
		this.maltService = maltService;
	}
	
	@GetMapping("/list")
	public String getMalts(Model theModel) {
		
		Set<Malt> theMalts = maltService.findAll();
		
		theModel.addAttribute("malts", theMalts);
		
		return "malt-list";
	}
	
}
