package com.wojciechowski.project.forum.repositories;

import org.springframework.data.repository.CrudRepository;

import com.wojciechowski.project.forum.domain.Topic;

public interface TopicRepository extends CrudRepository<Topic, Long> {

}
