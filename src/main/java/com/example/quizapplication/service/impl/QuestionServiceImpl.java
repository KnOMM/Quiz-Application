package com.example.quizapplication.service.impl;

import com.example.quizapplication.dto.QuestionDto;
import com.example.quizapplication.entity.Question;
import com.example.quizapplication.exception.ResourceNotFoundException;
import com.example.quizapplication.repository.QuestionRepository;
import com.example.quizapplication.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository
                .findAll()
                .stream()
                .map(q -> new QuestionDto(q.getContent(),
                        q.getTopic(),
                        q.getDifficultyRank()))
                .toList();
    }

    @Override
    public List<QuestionDto> getQuestionsByTopic(String topic) {
        return questionRepository.findByTopic(topic)
                .stream()
                .map(
                        q -> new QuestionDto(q.getContent(),
                                q.getTopic(),
                                q.getDifficultyRank())
                ).toList();
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question, long id) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question", id, "id"));
        existingQuestion.setContent(question.getContent());
        existingQuestion.setTopic(question.getTopic());
        existingQuestion.setDifficultyRank(question.getDifficultyRank());
        questionRepository.save(existingQuestion);
        return existingQuestion;
    }

    @Override
    public void deleteQuestion(long id) {
        questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question", id, "id"));
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDto getQuestionById(long id) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question", id, "id"));
        return new QuestionDto(existingQuestion.getContent(),
                existingQuestion.getTopic(),
                existingQuestion.getDifficultyRank());
    }
}
