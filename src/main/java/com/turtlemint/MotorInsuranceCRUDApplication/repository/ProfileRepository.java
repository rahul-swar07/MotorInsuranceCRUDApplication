package com.turtlemint.MotorInsuranceCRUDApplication.repository;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, Long> {
    List<Profile> findAll();
    Profile findById(long requestId);
    void deleteById(long requestId);
}
