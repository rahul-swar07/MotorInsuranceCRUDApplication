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

    public Profile(long requestId, String vertical, String vehicleMake, String vehicleModel) {
        this.requestId = requestId;
        this.vertical = vertical;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
    }
}

// /quotes/requestId (better) or /quotes and send requestbody. <---------> DONE
// fetch profile on basis of requestId --> fetch supportedInsurers on the basis of profile from datasetup collection <-----------> DONE

// /checkout/requestId and input insurer name <-----------> ONTO THIS
// {
//       insurer : insurerName,
//       custormermobile,
//       customername
// }
// save.

//payment api, requestId, and amount pay (mock), save (success/failure) on db.