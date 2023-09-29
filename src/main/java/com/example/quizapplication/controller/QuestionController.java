package com.example.quizapplication.controller;

import com.example.quizapplication.dto.QuestionDto;
import com.example.quizapplication.entity.Question;
import com.example.quizapplication.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    //    curl http://localhost:8080/api/questions?[topic={topic}]
    @GetMapping
    public ResponseEntity<List<QuestionDto>> getQuestions(@RequestParam(value = "topic", required = false) Optional<String> topic) {
        return topic
                .map(s -> new ResponseEntity<>(questionService.getQuestionsByTopic(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById (@PathVariable long id){
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    //    curl -X POST -H "Content-Type: application/json" -d '{"topic":"new","difficultyRank":30,"content":"Who is the best?"}' http://localhost:8080/api/questions
    @PostMapping()
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.saveQuestion(question), HttpStatus.CREATED);
    }

    //    curl -X PUT -H "Content-Type: application/json" -d '{"topic":"new","difficultyRank":30,"content":"Who is the best?"}' http://localhost:8080/api/questions/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable long id, @RequestBody Question question) {
        return new ResponseEntity<>(questionService.updateQuestion(question, id), HttpStatus.OK);
    }

    //    curl -X DELETE http://localhost:8080/api/questions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("Deleted successfully!!!", HttpStatus.OK);
    }
}
