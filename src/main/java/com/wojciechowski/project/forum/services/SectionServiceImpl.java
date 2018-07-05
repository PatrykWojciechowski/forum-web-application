package com.wojciechowski.project.forum.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.repositories.SectionRepository;

@Service
public class SectionServiceImpl implements SectionService {

	private SectionRepository sectionRepository;

	@Autowired
	public SectionServiceImpl(SectionRepository sectionRepository) {
		this.sectionRepository = sectionRepository;
	}

	@Override
	public Set<Section> getSections() {
		
		Set<Section> sectionSet = new HashSet<>();
		
		sectionRepository.findAll().iterator().forEachRemaining(sectionSet::add);
		
		return sectionSet;
	}

	@Override
	public Section findSectionById(Long id) {
		
		Optional<Section> sectionOptional = sectionRepository.findById(id);
		
		if (!sectionOptional.isPresent()) {
			//TODO implement error handling
			throw new RuntimeException("Section Not Found!");
		}
		
		return sectionOptional.get();
		
	}

}
