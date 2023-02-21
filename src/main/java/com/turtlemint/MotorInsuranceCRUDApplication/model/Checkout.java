package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "checkoutSetup")
public class Checkout {
    @Id
    private long requestId;
    private Customer checkoutCustomer;
    private Insurer checkoutInsurer;
}
