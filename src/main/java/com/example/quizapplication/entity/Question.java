package com.example.quizapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false)
    private Integer difficultyRank;
    @Column(nullable = false)
    private String content;
}
