package com.example.quizapplication.controller;

import com.example.quizapplication.dto.QuestionDto;
import com.example.quizapplication.dto.ResponseDto;
import com.example.quizapplication.entity.Response;
import com.example.quizapplication.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping("quiz")
    public ResponseEntity<Map<QuestionDto, List<ResponseDto>>> getQuiz() {
        return new ResponseEntity<>(responseService.getQuiz(), HttpStatus.OK);
    }

    //    curl -X POST -H "Content-Type: application/json" -d '{"text":"answer for sth","isCorrect":true,"question":{"id":"3","topic":"new","difficultyRank":50,"content":"Who is the best?"}}' http://localhost:8080/api/responses
    @PostMapping("responses")
    public ResponseEntity<Response> createResponse(@RequestBody Response response) {
        return new ResponseEntity<>(responseService.saveResponse(response), HttpStatus.CREATED);
    }

    // changes response content
    //    curl -X PUT -H "Content-Type: application/json" -d '{"text":"answer for sth2","isCorrect":true,"question":{"id":"3","topic":"new","difficultyRank":50,"content":"Who is the best?"}}' http://localhost:8080/api/responses/8
    // changes question the response belongs to
    // curl -X PUT -H "Content-Type: application/json" -d '{"text":"answer for sth2","isCorrect":true,"question":{"id":"2","topic":"technology","difficultyRank":20,"content":"question2"}}' http://localhost:8080/api/responses/8
    @PutMapping("responses/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable long id, @RequestBody Response response) {
        return new ResponseEntity<>(responseService.updateResponse(response, id), HttpStatus.OK);
    }

    //    curl -X DELETE http://localhost:8080/api/responses/7
    @DeleteMapping("responses/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable long id) {
        responseService.deleteResponse(id);
        return new ResponseEntity<>("Deleted successfully!!!", HttpStatus.OK);
    }

}
