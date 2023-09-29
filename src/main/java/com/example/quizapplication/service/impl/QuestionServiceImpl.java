package com.example.quizapplication.service.impl;

import com.example.quizapplication.dto.ResponseDto;
import com.example.quizapplication.dto.SingleQuestion;
import com.example.quizapplication.entity.Question;
import com.example.quizapplication.entity.Response;
import com.example.quizapplication.repository.QuestionRepository;
import com.example.quizapplication.repository.ResponseRepository;
import com.example.quizapplication.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ResponseRepository responseRepository;

    public Map<Question, List<ResponseDto>> getAllQuestionsDemo() {
        Map<Question, List<ResponseDto>> responsesWithoutQuestion = responseRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Response::getQuestion,
                        Collectors.mapping(response -> new ResponseDto(response.getText(), response.getIsCorrect())
                                , Collectors.toList())
                ));
        return responsesWithoutQuestion;
    }
}
