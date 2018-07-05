package com.wojciechowski.project.forum.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.repositories.SectionRepository;

public class SectionServiceImplTest {

	SectionService sectionService;
	
	@Mock SectionRepository sectionRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sectionService = new SectionServiceImpl(sectionRepository);
	}

	@Test
	public void testGetSections() {
		
		//given
		Section section = new Section();
		Set<Section> sections = new HashSet<>();
		sections.add(section);
		
		when(sectionService.getSections()).thenReturn(sections);
		
		//when
		Set<Section> sectionsReturned = sectionService.getSections();
		
		//then
		assertFalse(sectionsReturned.isEmpty());
		assertEquals(sectionsReturned.size(), 1);
		verify(sectionRepository, times(1)).findAll();
		verify(sectionRepository, never()).findById(anyLong());
		
	}
	
	@Test
	public void testFindSectionById() {
		
		//given
		Section section = new Section();
		section.setId(1L);
		Optional<Section> sectionOptional = Optional.of(section);
		
		when(sectionRepository.findById(anyLong())).thenReturn(sectionOptional);
		
		//when
		Section sectionReturned = sectionService.findSectionById(1L);
		
		//then
		assertNotNull(sectionReturned);
		verify(sectionRepository, times(1)).findById(anyLong());
		verify(sectionRepository, never()).findAll();
		
	}

}
