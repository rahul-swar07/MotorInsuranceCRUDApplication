package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.Data;

@Data
public class Insurer {
    private String name;
    private long premium;

    public Insurer(String name, long premium) {
        this.name = name;
        this.premium = premium;
    }
}
