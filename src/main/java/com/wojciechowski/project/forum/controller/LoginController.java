package com.wojciechowski.project.forum.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wojciechowski.project.forum.domain.Section;
import com.wojciechowski.project.forum.repositories.SectionRepository;

@Controller
public class LoginController {

	@Autowired
	private SectionRepository sectionRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String registration(Model model) {
		
		Iterable<Section> sections = sectionRepository.findAll();
		
		model.addAttribute("sections", sections);
		
        return "homepage";
    }
	
	
	
}