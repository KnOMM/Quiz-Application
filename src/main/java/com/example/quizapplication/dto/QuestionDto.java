package com.example.quizapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class QuestionDto {
    private String content;
    private String topic;
    private int rank;
}
