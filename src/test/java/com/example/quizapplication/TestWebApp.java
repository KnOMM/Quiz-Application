package com.example.quizapplication;

import com.example.quizapplication.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWebApp extends QuizApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void a_testNotExistingQuestion() throws Exception {
        mockMvc.perform(get("/api/questions/10")).andExpect(status().isNotFound());
    }

    @Test
    public void b_testAllQuestions() throws Exception {
        mockMvc.perform(get("/api/questions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void c_testSingleQuestionStructure() throws Exception {
        mockMvc.perform(get("/api/questions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").value("question1"))
                .andExpect(jsonPath("topic").value("nature"))
                .andExpect(jsonPath("rank").value(10));
    }

    @Test
    public void d_testCreateQuestion() throws Exception {
        String requestBody = "{\"topic\":\"new\",\"difficultyRank\":30,\"content\":\"Who is the best?\"}";
        mockMvc.perform(post("/api/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.topic", is("new")))
                .andExpect(jsonPath("$.difficultyRank", is(30)))
                .andExpect(jsonPath("$.content", is("Who is the best?")));
    }

    @Test
    public void e_testPutExistingQuestion() throws Exception {
        String requestBody = "{\"topic\":\"old\",\"difficultyRank\":100,\"content\":\"Who is the best?\"}";
        mockMvc.perform(put("/api/questions/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.topic", is("old")))
                .andExpect(jsonPath("$.difficultyRank", is(100)))
                .andExpect(jsonPath("$.content", is("Who is the best?")));
    }

    @Test
    public void f_testPutNotExistingQuestion() throws Exception {
        String requestBody = "{\"topic\":\"again new\",\"difficultyRank\":0,\"content\":\"Is it boring to read?\"}";
        mockMvc.perform(put("/api/questions/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(jsonPath("$", is(new ResourceNotFoundException("Question", 5, "id").toString())));
    }

    @Test
    public void g_testDeleteQuestion() throws Exception {
        mockMvc.perform(delete("/api/questions/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void h_testDeleteNotExistingQuestion() throws Exception {
        mockMvc.perform(delete("/api/questions/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
