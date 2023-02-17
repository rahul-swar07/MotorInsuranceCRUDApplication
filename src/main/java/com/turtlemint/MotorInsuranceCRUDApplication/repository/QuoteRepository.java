package com.turtlemint.MotorInsuranceCRUDApplication.repository;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, Long> {
    Quote findByQuoteVerticalAndQuoteVehicleMakeAndQuoteVehicleModel(String vertical, String vehicleMake, String vehicleModel);
}
