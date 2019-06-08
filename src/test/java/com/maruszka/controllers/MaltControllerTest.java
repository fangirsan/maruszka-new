package com.maruszka.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.maruszka.controller.MaltController;
import com.maruszka.model.Malt;
import com.maruszka.services.CountryService;
import com.maruszka.services.MaltService;

@ExtendWith(MockitoExtension.class)
class MaltControllerTest {

	@Mock
	MaltService maltService;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	MaltController controller;

	private Set<Malt> malts;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		malts = new HashSet<>();
		malts.add(Malt.builder().id(1L).build());
		malts.add(Malt.builder().id(2L).build());

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	void testListMalts() throws Exception{
		
		when(maltService.findAll()).thenReturn(malts);
		
		mockMvc.perform(get("/malt/list"))
			.andExpect(status().is(200))
			.andExpect(view().name("malt/malt-list"));
		
		verifyZeroInteractions(maltService);
	}
	
	@Test
	void displayMalt() throws Exception {
		when(maltService.findById(anyLong())).thenReturn(Malt.builder().id(1L).build());
		
		mockMvc
			.perform(get("/malt/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("malt/malt-show"))
			.andExpect(model().attribute("malt", hasProperty("id", is(1L))));
	}
	
	@Test
	void testFindMalts() throws Exception{
		
		mockMvc.perform(get("/malt/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("malt/malt-list"))
			.andExpect(model().attributeExists("malt"));
		
		verifyZeroInteractions(maltService);
	}
	
	@Test
	void processFindFormReturnMany() throws Exception {
		when(maltService.findAllByMaltNameLike(anyString()))
				.thenReturn(Arrays.asList(Malt.builder().id(1l).build(), 
										  Malt.builder().id(2l).build()));
		
		mockMvc.perform(get("/malt"))
			.andExpect(status().isOk())
			.andExpect(view().name("malt/malt-list"))
			.andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void processFindFormReturnOne() throws Exception {
		when(maltService.findAllByMaltNameLike(anyString())).thenReturn(Arrays.asList(Malt.builder().id(1l).build()));
		
		mockMvc.perform(get("/malt"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/malt/1"));
	}
	
	@Test
	void initCreationForm() throws Exception {
		
		mockMvc.perform(get("/malt/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("malt/createOrUpdateMaltForm"))
		.andExpect(model().attributeExists("malt"));
		
		verifyZeroInteractions(maltService);
	}

	@Test
    void processCreationForm() throws Exception {
        
		when(maltService.save(ArgumentMatchers.any())).thenReturn(Malt.builder().id(1l).build());

        mockMvc.perform(post("/malt/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/malt/1"))
                .andExpect(model().attributeExists("malt"));

        verify(maltService).save(ArgumentMatchers.any());
    }
	
	@Test
	void initUpdateOwnerForm() throws Exception {
		
		when(maltService.findById(anyLong())).thenReturn(Malt.builder().id(1l).build());
		
		mockMvc.perform(get("/malt/1/edit"))
		.andExpect(status().isOk())
		.andExpect(view().name("malt/createOrUpdateMaltForm"))
		.andExpect(model().attributeExists("malt"));
		
		verifyZeroInteractions(maltService);
	}
	
	@Test
	void processUpdateOwnerForm() throws Exception {
		
		when(maltService.save(ArgumentMatchers.any())).thenReturn(Malt.builder().id(1l).build());
		
		mockMvc.perform(post("/malt/1/edit"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/malt/1"))
		.andExpect(model().attributeExists("malt"));
		
		verify(maltService).save(ArgumentMatchers.any());
	}
}
