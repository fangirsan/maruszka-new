package com.maruszka.controllers;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Ignore
@ExtendWith(MockitoExtension.class)
class MaltControllerTest {

//	@Mock
//	MaltService maltService;
//
//	@Mock
//	CountryService countryService;
//
//	@InjectMocks
//	MaltController controller;
//
//	private Set<Malt> malts;
//
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		malts = new HashSet<>();
//		malts.add(Malt.builder().id(1L).build());
//		malts.add(Malt.builder().id(2L).build());
//
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//	}
//
//	@Test
//	void testListMalts() throws Exception{
//
//		when(maltService.findAll()).thenReturn(malts);
//
//		mockMvc.perform(get("/malt/list"))
//			.andExpect(status().is(200))
//			.andExpect(view().name("malt/malt-list"));
//
//		verifyZeroInteractions(maltService);
//	}
//
//	@Test
//	void displayMalt() throws Exception {
//		when(maltService.findById(anyLong())).thenReturn(Malt.builder().id(1L).build());
//
//		mockMvc
//			.perform(get("/malt/123"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("malt/malt-show"))
//			.andExpect(model().attribute("malt", hasProperty("id", is(1L))));
//	}
//
//	@Test
//	void testFindMalts() throws Exception{
//
//		mockMvc.perform(get("/malt/find"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("malt/malt-list"))
//			.andExpect(model().attributeExists("malt"));
//
//		verifyZeroInteractions(maltService);
//	}
//
//	@Test
//	void processFindFormReturnMany() throws Exception {
//		when(maltService.findAllByMaltNameLike(anyString()))
//				.thenReturn(Arrays.asList(Malt.builder().id(1l).build(),
//										  Malt.builder().id(2l).build()));
//
//		mockMvc.perform(get("/malt"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("malt/malt-list"))
//			.andExpect(model().attribute("selections", hasSize(2)));
//	}
//
//	@Test
//	void processFindFormReturnOne() throws Exception {
//		when(maltService.findAllByMaltNameLike(anyString())).thenReturn(Arrays.asList(Malt.builder().id(1l).build()));
//
//		mockMvc.perform(get("/malt"))
//			.andExpect(status().is3xxRedirection())
//			.andExpect(view().name("redirect:/malt/1"));
//	}
//
//	@Test
//	void initCreationForm() throws Exception {
//
//		mockMvc.perform(get("/malt/new"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("malt/createOrUpdateMaltForm"))
//		.andExpect(model().attributeExists("malt"));
//
//		verifyZeroInteractions(maltService);
//	}
//
//	@Test
//    void processCreationForm() throws Exception {
//
//		when(maltService.save(ArgumentMatchers.any())).thenReturn(Malt.builder().id(1l).build());
//
//        mockMvc.perform(post("/malt/new"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/malt/1"))
//                .andExpect(model().attributeExists("malt"));
//
//        verify(maltService).save(ArgumentMatchers.any());
//    }
//
//	@Test
//	void initUpdateOwnerForm() throws Exception {
//
//		when(maltService.findById(anyLong())).thenReturn(Malt.builder().id(1l).build());
//
//		mockMvc.perform(get("/malt/1/edit"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("malt/createOrUpdateMaltForm"))
//		.andExpect(model().attributeExists("malt"));
//
//		verifyZeroInteractions(maltService);
//	}
//
//	@Test
//	void processUpdateOwnerForm() throws Exception {
//
//		when(maltService.save(ArgumentMatchers.any())).thenReturn(Malt.builder().id(1l).build());
//
//		mockMvc.perform(post("/malt/1/edit"))
//		.andExpect(status().is3xxRedirection())
//		.andExpect(view().name("redirect:/malt/1"))
//		.andExpect(model().attributeExists("malt"));
//
//		verify(maltService).save(ArgumentMatchers.any());
//	}
//
//	@Test
//	public void testGetMaltNotFound() throws Exception {
//		when(maltService.findById(anyLong())).thenThrow(NotFoundException.class);
//
//		mockMvc.perform(get("/malt/10"))
//				.andExpect(status().isNotFound())
//				.andExpect(view().name("404error"));
//	}

}
