package com.shekhargulati.okrapp.core.service;

import com.shekhargulati.okrapp.core.domain.Objective;
import com.shekhargulati.okrapp.core.repository.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
@Transactional
public class ObjectiveService {

    private ObjectiveRepository objectiveRepository;

    @Autowired
    public ObjectiveService(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    public ObjectiveDto create(Objective objective) {
        if (objective.getStartDate() == null && objective.getEndDate() == null) {
            Quarter quarter = findQuarter();
            objective.setStartDate(quarter.startDate());
            objective.setEndDate(quarter.endDate());
        } else if (objective.getStartDate() == null) {
            LocalDate startDate = objective.getEndDate().minusMonths(2).with(TemporalAdjusters.firstDayOfMonth());
            objective.setStartDate(startDate);
        } else if (objective.getEndDate() == null) {
            LocalDate endDate = objective.getStartDate().plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
            objective.setEndDate(endDate);
        }
        Objective saved = this.objectiveRepository.save(objective);
        return new ObjectiveDto(saved);
    }

    private Quarter findQuarter() {
        LocalDate todayDate = LocalDate.now();
        LocalDate firstDayOfQuarter = todayDate.with(todayDate.getMonth().firstMonthOfQuarter())
                .with(TemporalAdjusters.firstDayOfMonth());

        LocalDate lastDayOfQuarter = firstDayOfQuarter.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
        return new Quarter(firstDayOfQuarter, lastDayOfQuarter);
    }
}

record Quarter(LocalDate startDate, LocalDate endDate) {
}
