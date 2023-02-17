package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "checkoutSetup")
public class Checkout {
    @Id
    private long checkoutId;
    private Customer checkoutCustomer;
    private Insurer checkoutInsurer;

    public Checkout(long checkoutId, Customer checkoutCustomer, Insurer checkoutInsurer) {
        this.checkoutId = checkoutId;
        this.checkoutCustomer = checkoutCustomer;
        this.checkoutInsurer = checkoutInsurer;
    }
}
