package com.wojciechowski.project.forum.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.services.SectionService;

public class TopicControllerTest {

	@Mock SectionService sectionService;

    TopicController controller;

    MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new TopicController(sectionService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testShowTopicsForSection() throws Exception {
		//given
        Section section = new Section();
        when(sectionService.findSectionById(anyLong())).thenReturn(section);

        //when
        mockMvc.perform(get("/section/1/show-topics"))
                .andExpect(status().isOk())
                .andExpect(view().name("topics"))
                .andExpect(model().attributeExists("section"));

        //then
        verify(sectionService, times(1)).findSectionById(anyLong());
	}

}
