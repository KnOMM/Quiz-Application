package com.example.quizapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class ResponseDto {
    private String text;
    private boolean isCorrect;
}
