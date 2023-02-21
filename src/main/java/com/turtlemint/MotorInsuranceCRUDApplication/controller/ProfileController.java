package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turtle")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profiles")
    public ResponseEntity<?> createProfile(@RequestBody Profile profile) throws Exception{
        try{
            if(profile.getVertical() == null || profile.getVehicleMake() == null || profile.getVehicleModel() == null){
                return new ResponseEntity<>("Profile Creation Failed! One or More Null Fields!", HttpStatus.EXPECTATION_FAILED);
            }
            if(profile.getVertical().equals("") || profile.getVehicleMake().equals("") || profile.getVehicleModel().equals("")){
                return new ResponseEntity<>("Profile Creation Failed! One or More Empty Fields!", HttpStatus.EXPECTATION_FAILED);
            }
            if(!profile.getVertical().equals("FW") && !profile.getVertical().equals("TW") && !profile.getVertical().equals("CV")){
                return new ResponseEntity<>("Profile Creation Failed! Invalid Vertical!", HttpStatus.EXPECTATION_FAILED);
            }
            Profile profileCreated = profileService.createProfile(profile);
            return new ResponseEntity<>(profileCreated, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/profiles")
    public ResponseEntity<?> getAllProfiles() throws Exception{
        try{
            return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/profiles/{requestId}")
    public ResponseEntity<?> getProfile(@PathVariable long requestId) throws Exception{
        Profile profile = profileService.getProfile(requestId);
        try{
            if(profile == null){
                return new ResponseEntity<>("Profile does not exist! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping("/profiles/{requestId}")
    public ResponseEntity<?> updateProfile(@PathVariable long requestId, @RequestBody Profile profile) throws Exception{
        Profile profileTemp = profileService.getProfile(requestId);
        try{
            if(profileTemp == null){
                return new ResponseEntity<>("Profile Update Failed! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            Profile profileUpdated = profileService.updateProfile(requestId, profile);
            return new ResponseEntity<>(profileUpdated, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/profiles/{requestId}")
    public ResponseEntity<?> deleteProfile(@PathVariable long requestId) throws Exception{
        Profile profile = profileService.getProfile(requestId);
        try{
            if(profile == null){
                return new ResponseEntity<>("Profile Deletion Failed! Invalid requestId!", HttpStatus.NOT_FOUND);
            }
            profileService.deleteProfile(requestId);
            return new ResponseEntity<>("Deleted Profile with requestId = " + requestId + " Successfully!", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
