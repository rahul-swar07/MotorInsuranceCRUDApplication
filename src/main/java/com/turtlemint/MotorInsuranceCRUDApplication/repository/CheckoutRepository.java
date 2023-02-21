package com.turtlemint.MotorInsuranceCRUDApplication.repository;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Checkout;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CheckoutRepository extends MongoRepository<Checkout, Long> {
    Checkout findById(long requestId);
    List<Checkout> findAll();
    void deleteById(long requestId);
}
