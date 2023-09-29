package com.example.quizapplication.service;

import com.example.quizapplication.dto.QuestionDto;
import com.example.quizapplication.entity.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();
    List<QuestionDto> getQuestionsByTopic(String topic);
    Question saveQuestion(Question question);
    Question updateQuestion(Question question, long id);
    void deleteQuestion(long id);
    QuestionDto getQuestionById(long id);
}
