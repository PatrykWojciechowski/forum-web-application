package com.wojciechowski.project.forum.repositories;

import org.springframework.data.repository.CrudRepository;

import com.wojciechowski.project.forum.domain.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
