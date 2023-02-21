package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "dataSetup")
public class Quotation {
    @Id
    private String id;
    private String vertical;
    private String vehicleMake;
    private String vehicleModel;
    private List<Insurer> supportedInsurers;
}
