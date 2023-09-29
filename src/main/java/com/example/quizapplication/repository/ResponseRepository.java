package com.example.quizapplication.repository;

import com.example.quizapplication.entity.Response;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findAll(Sort sort);
}
