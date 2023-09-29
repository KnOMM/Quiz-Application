package com.example.quizapplication.service.impl;

import com.example.quizapplication.dto.QuestionDto;
import com.example.quizapplication.dto.ResponseDto;
import com.example.quizapplication.entity.Question;
import com.example.quizapplication.entity.Response;
import com.example.quizapplication.exception.ResourceNotFoundException;
import com.example.quizapplication.repository.ResponseRepository;
import com.example.quizapplication.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    private final ResponseRepository responseRepository;

    @Override
    public Map<QuestionDto, List<ResponseDto>> getQuiz() {
        Map<QuestionDto, List<ResponseDto>> responsesWithoutQuestion = responseRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        response -> {
                            Question question = response.getQuestion();
                            return new QuestionDto(question.getContent(), question.getTopic(), question.getDifficultyRank());
                        },
                        Collectors.mapping(response -> new ResponseDto(response.getText(), response.getIsCorrect())
                                , Collectors.toList())
                ));
        return responsesWithoutQuestion;
    }

    @Override
    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    @Override
    public Response updateResponse(Response response, long id) {
        Response existingResponse = responseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question", id, "id"));
        existingResponse.setQuestion(response.getQuestion());
        existingResponse.setText(response.getText());
        existingResponse.setIsCorrect(response.getIsCorrect());
        responseRepository.save(existingResponse);
        return existingResponse;
    }

    @Override
    public void deleteResponse(long id) {
        responseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Response", id, "id"));
        responseRepository.deleteById(id);
    }
}
