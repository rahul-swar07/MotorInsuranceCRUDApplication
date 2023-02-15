package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "profileSetup")
public class Profile {
    @Id
    private long requestId;
    private String vertical;
    private String vehicleMake;
    private String vehicleModel;
}
