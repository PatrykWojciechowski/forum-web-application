package com.wojciechowski.project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.services.SectionService;

@Controller
public class TopicController {

	private SectionService sectionService;
	
	@Autowired
	public TopicController(SectionService sectionService) {
		this.sectionService = sectionService;
	}
	
	@GetMapping("/section/{id}/show-topics")
    public String showTopicsForSection(Model model, @PathVariable String id) {
		
		Section section = sectionService.findSectionById(Long.valueOf(id));
		
		model.addAttribute("section", section);
		
        return "topics";
    }
	
}
