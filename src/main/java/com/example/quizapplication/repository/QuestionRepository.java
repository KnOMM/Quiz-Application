package com.example.quizapplication.repository;

import com.example.quizapplication.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTopic(String topic);
}
