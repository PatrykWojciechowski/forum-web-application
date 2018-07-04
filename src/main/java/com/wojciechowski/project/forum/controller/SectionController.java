package com.wojciechowski.project.forum.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.services.SectionService;

@Controller
public class SectionController {
	
	private SectionService sectionService;

	@Autowired
	public SectionController(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@GetMapping({"/","/home"})
    public String showSections(Model model) {
		
		Set<Section> sections = sectionService.getSections();
		
		model.addAttribute("sections", sections);
		
        return "sections";
    }
	
	
	
}