package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profileSetup")
public class Profile {
    @Id
    private long requestId;
    private String vertical;
    private String vehicleMake;
    private String vehicleModel;
}




// profile creation.

// /quotes/requestId (better) or /quotes and send RequestBody. <---------> DONE
// fetch profile on basis of requestId --> fetch supportedInsurers on the basis of profile from datasetup collection <-----------> DONE

// /checkout/requestId and input insurer name <-----------> DONE
// {
//       insurer : insurerName, insurerPremium
//       customer : customerName, customerEmail, customerMobile,
//       requestId
// }
// save.

//payment api, requestId, and amount pay (mock), save (success/failure) on db. <----------> ONTO THIS

//unit testing using junit.