package com.wojciechowski.project.forum.repositories;

import org.springframework.data.repository.CrudRepository;

import com.wojciechowski.project.forum.domain.Section;

public interface SectionRepository extends CrudRepository<Section, Long> {

}
