package com.example.quizapplication.service;

import com.example.quizapplication.dto.QuestionDto;
import com.example.quizapplication.dto.ResponseDto;
import com.example.quizapplication.entity.Response;

import java.util.List;
import java.util.Map;

public interface ResponseService {
    Map<QuestionDto, List<ResponseDto>> getQuiz();
    Response saveResponse(Response response);
    Response updateResponse(Response response, long id);
    void deleteResponse(long id);
}
