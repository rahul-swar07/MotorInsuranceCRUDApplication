package com.turtlemint.MotorInsuranceCRUDApplication.repository;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuotationRepository extends MongoRepository<Quotation, String> {
    Quotation findByVerticalAndVehicleMakeAndVehicleModel(String vertical, String vehicleMake, String vehicleModel);
    Optional<Quotation> findById(String id);
    List<Quotation> findAll();
    void deleteById(String id);
}
