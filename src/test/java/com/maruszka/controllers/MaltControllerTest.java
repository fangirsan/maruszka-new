package com.maruszka.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.maruszka.controller.MaltController;
import com.maruszka.model.Malt;
import com.maruszka.services.CountryService;
import com.maruszka.services.MaltService;

@ExtendWith(MockitoExtension.class)
public class MaltControllerTest {

	@Mock
	MaltService maltService;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	MaltController controller;
	
	Set<Malt> malts;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		malts = new HashSet<>();
		malts.add(Malt.builder().id(1L).build());
		malts.add(Malt.builder().id(2L).build());

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
//	@Test
//	void testListMalts() throws Exception{
//		mockMvc.perform(get("/malt/list"))
//		.andExpect(status().is(200))
//		.andExpect(view().name("malt-list"));
//		
//		verifyZeroInteractions(maltService);
//	}
	
	@Test
	void displayMalt() throws Exception {
		when(maltService.findById(anyLong())).thenReturn(Malt.builder().id(1L).build());
		
		mockMvc
			.perform(get("/malt/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("malt-show"))
			.andExpect(model().attribute("malt", hasProperty("id", is(1L))));
	}
}
