package com.example.quizapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Boolean isCorrect;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Question question;
}
