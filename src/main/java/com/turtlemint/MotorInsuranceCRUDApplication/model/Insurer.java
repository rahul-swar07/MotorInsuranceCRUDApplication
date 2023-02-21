package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurer {
    private String name;
    private long premium;
}
