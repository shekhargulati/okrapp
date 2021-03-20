package com.shekhargulati.okrapp.core.service;

import com.shekhargulati.okrapp.core.domain.Objective;
import com.shekhargulati.okrapp.core.repository.ObjectiveRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveServiceTests {

    @Mock
    private ObjectiveRepository objectiveRepository;

    @InjectMocks
    private ObjectiveService objectiveService;

    @Test
    void should_set_current_quarter() {
        when(objectiveRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
        ObjectiveDto objectiveDto = objectiveService.create(new Objective("Objective 1"));
        assertThat(objectiveDto.startDate()).isNotNull();
        assertThat(objectiveDto.endDate()).isNotNull();
        assertThat(objectiveDto.startDate()).isBefore(objectiveDto.endDate());
    }

    @Test
    void should_set_start_date() {
        when(objectiveRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
        ObjectiveDto objectiveDto = objectiveService.create(new Objective("Objective 1", null, LocalDate.now()));
        assertThat(objectiveDto.startDate()).isNotNull();
        assertThat(objectiveDto.startDate()).isBefore(objectiveDto.endDate());
    }

    @Test
    void should_set_end_date() {
        when(objectiveRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
        ObjectiveDto objectiveDto = objectiveService.create(new Objective("Objective 1", LocalDate.now(), null));
        assertThat(objectiveDto.endDate()).isNotNull();
        assertThat(objectiveDto.startDate()).isBefore(objectiveDto.endDate());
    }
}