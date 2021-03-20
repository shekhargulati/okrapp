package com.shekhargulati.okrapp.core.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Metric {

    private int value;
    private String type;

    public Metric(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
