package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "quotesSetup")
public class Quote {
    @Id
    private long quoteId;
    private String quoteVertical;
    private String quoteVehicleMake;
    private String quoteVehicleModel;
    
    private List<Insurer> supportedInsurers;

    public Quote(long quoteId, String quoteVertical, String quoteVehicleMake, String quoteVehicleModel, List<Insurer> supportedInsurers) {
        this.quoteId = quoteId;
        this.quoteVertical = quoteVertical;
        this.quoteVehicleMake = quoteVehicleMake;
        this.quoteVehicleModel = quoteVehicleModel;
        this.supportedInsurers = supportedInsurers;
    }
}
