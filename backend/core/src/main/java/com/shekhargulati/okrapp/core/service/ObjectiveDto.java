package com.shekhargulati.okrapp.core.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shekhargulati.okrapp.core.domain.Objective;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ObjectiveDto(String id, String title, LocalDate startDate, LocalDate endDate) {
    public ObjectiveDto(Objective objective) {
        this(objective.getId(),objective.getTitle(),objective.getStartDate(),objective.getEndDate());
    }
}
