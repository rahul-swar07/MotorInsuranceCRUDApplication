package com.turtlemint.MotorInsuranceCRUDApplication.repository;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends MongoRepository<Quotation, Long> {
    Quotation findByQuoteVerticalAndQuoteVehicleMakeAndQuoteVehicleModel(String vertical, String vehicleMake, String vehicleModel);
}
