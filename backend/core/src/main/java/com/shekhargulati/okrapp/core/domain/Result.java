package com.shekhargulati.okrapp.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result extends AbstractBaseEntity {

    private String title;

    private boolean achieved;

    @Embedded
    private Metric metric;

    @ManyToOne
    private Objective objective;

    Result() {
    }

    public Result(String title, Metric metric) {
        this.title = title;
        this.metric = metric;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public Metric getMetric() {
        return metric;
    }

    public Objective getObjective() {
        return objective;
    }
}
