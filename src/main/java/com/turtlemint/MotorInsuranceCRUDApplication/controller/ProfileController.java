package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profiles")
    public String createProfile(@RequestBody Profile profile){
        profileService.createProfile(profile);
        return "Created Profile with requestId = " + profile.getRequestId();
    }

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }

    @GetMapping("/profiles/{id}")
    public Optional<Profile> getAProfile(@PathVariable long id){
        return profileService.getAProfile(id);
    }

    @PutMapping("/profiles/{id}")
    public String updateProfile(@PathVariable long id, @RequestBody Profile profile){
        profileService.updateProfile(profile);
        return "Updated Profile with requestId = " + id;
    }

    @DeleteMapping("/profiles/{id}")
    public String deleteProfile(@PathVariable long id){
        profileService.deleteProfile(id);
        return "Deleted Profile with requestId = " + id;
    }
}
