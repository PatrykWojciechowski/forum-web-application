package com.wojciechowski.project.forum.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.wojciechowski.project.forum.services.SectionService;

public class SectionControllerTest {

	@Mock SectionService sectionService;
	@Mock Model model;
	SectionController sectionController;
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sectionController = new SectionController(sectionService);
		mockMvc = MockMvcBuilders.standaloneSetup(sectionController).build();
	}

	@Test
	public void testMockMVC() throws Exception {
		
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("sections"))
			.andExpect(model().attributeExists("sections"));
	}
	
	
	@Test
	public void testShowSections() {
		
		String viewName = sectionController.showSections(model);
		assertEquals("sections", viewName);

		verify(sectionService, times(1)).getSections();
		verify(model, times(1)).addAttribute(eq("sections"), anySet());
	}

}
