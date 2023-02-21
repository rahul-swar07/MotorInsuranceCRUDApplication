package com.turtlemint.MotorInsuranceCRUDApplication.service;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Profile createProfile(Profile profile){
        long requestId = Math.round(Math.random() * 1e9);
        while(profileRepository.findById(requestId) != null){
            requestId = Math.round(Math.random() * 1e9);
        }
        profile.setRequestId(requestId);
        profileRepository.save(profile);
        return profile;
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public Profile getProfile(long requestId){
        return profileRepository.findById(requestId);
    }

    public Profile updateProfile(long requestId, Profile profile) {
        profile.setRequestId(requestId);
        profileRepository.save(profile);
        return profile;
    }

    public void deleteProfile(long requestId){
        profileRepository.deleteById(requestId);
    }
}
