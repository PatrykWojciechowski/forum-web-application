package com.wojciechowski.project.forum.services;

import java.util.Set;

import com.wojciechowski.project.forum.domain.Section;

public interface SectionService {

	Set<Section> getSections();

	Section findSectionById(Long id);

}
