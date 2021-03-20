package com.shekhargulati.okrapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ObjectiveResourceTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create objective when passed valid data")
    void should_create_objective_when_passed_valid_data() throws Exception {
        String json = this.objectMapper.writeValueAsString(new ObjectiveRequest("Objective 1", LocalDate.now(), LocalDate.now().plusMonths(2)));
        this.mockMvc.perform(
                post("/api/v1/objectives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("Objective 1"));
    }

    @Test
    @DisplayName("Should return 400 when title not passed")
    void test2() throws Exception {
        String json = this.objectMapper.writeValueAsString(new ObjectiveRequest(null, LocalDate.now(), LocalDate.now().plusMonths(2)));
        this.mockMvc.perform(
                post("/api/v1/objectives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations.length()").value(1))
                .andExpect(jsonPath("$.violations[0].field").value("title"))
                .andExpect(jsonPath("$.violations[0].message").value("must not be blank"));

    }

    @Test
    @DisplayName("Should use current quarter if start and end date are not set")
    void test3() throws Exception {
        String json = this.objectMapper.writeValueAsString(new ObjectiveRequest("Objective 1"));
        this.mockMvc.perform(
                post("/api/v1/objectives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("Objective 1"))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty());
    }
}