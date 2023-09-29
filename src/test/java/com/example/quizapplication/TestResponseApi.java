package com.example.quizapplication;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestResponseApi extends QuizApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void a_testQuiz() throws Exception {
        mockMvc.perform(get("/api/quiz"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void b_testCreateResponse() throws Exception {
        String requestBody = "{\"text\":\"answer for sth\",\"isCorrect\":true,\"question\":{\"id\":\"3\",\"topic\":\"new\",\"difficultyRank\":50,\"content\":\"question3\"}}";
        mockMvc.perform(post("/api/responses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void c_testCreateInvalidQuestionIdResponse() throws Exception {
        String requestBody = "{\"text\":\"answer for sth\",\"isCorrect\":false,\"question\":{\"id\":\"5\",\"topic\":\"new\",\"difficultyRank\":50,\"content\":\"question3\"}}";
        mockMvc.perform(post("/api/responses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    public void d_testPutResponse() throws Exception {
        String requestBody = "{\"text\":\"answer for sth2\",\"isCorrect\":true,\"question\":{\"id\":\"3\",\"topic\":\"new\",\"difficultyRank\":50,\"content\":\"Who is the best?\"}}";
        mockMvc.perform(put("/api/responses/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void e_testPutWithIncorrectResponse() throws Exception {
        String requestBody = "{\"text\":\"answer for sth2\",\"isCorrect\":true,\"question\":{\"id\":\"3\",\"topic\":\"new\",\"difficultyRank\":50,\"content\":\"Who is the best?\"}}";
        mockMvc.perform(put("/api/responses/20d")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void f_testPutWithIncorrectResponse() throws Exception {
        String requestBody = "{\"text\":\"answer for sth2\",\"isCorrect\":true,\"question\":{\"id\":\"3\",\"topic\":\"new\",\"difficultyRank\":50,\"content\":\"Who is the best?\"}}";
        mockMvc.perform(put("/api/responses/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    public void g_testDeleteIncorrectResponse() throws Exception {
        mockMvc.perform(delete("/api/responses/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void h_testDeleteResponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/responses/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("Deleted successfully!!!", mvcResult.getResponse().getContentAsString());
    }
}
