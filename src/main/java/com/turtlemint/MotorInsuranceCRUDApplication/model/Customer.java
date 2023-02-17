package com.turtlemint.MotorInsuranceCRUDApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customerSetup")
public class Customer {
    @Id
    private long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public Customer(String customerName, String customerEmail, String customerPhone) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }
}
