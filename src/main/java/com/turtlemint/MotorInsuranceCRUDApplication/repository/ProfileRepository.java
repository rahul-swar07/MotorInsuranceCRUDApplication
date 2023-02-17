package com.turtlemint.MotorInsuranceCRUDApplication.repository;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, Long> {
    // Profile CRUD
}
