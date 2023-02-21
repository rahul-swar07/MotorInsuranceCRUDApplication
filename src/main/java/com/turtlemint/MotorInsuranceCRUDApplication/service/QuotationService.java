package com.turtlemint.MotorInsuranceCRUDApplication.service;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Insurer;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.model.Quotation;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Insurer> getAllInsurers(long requestId) {
        Profile profile = profileRepository.findById(requestId);
        String vertical = profile.getVertical(), vehicleMake = profile.getVehicleMake(), vehicleModel = profile.getVehicleModel();
        Quotation quotation = quotationRepository.findByVerticalAndVehicleMakeAndVehicleModel(vertical, vehicleMake, vehicleModel);
        return quotation.getSupportedInsurers();
    }

    public Quotation createQuotation(Quotation quotation){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(24);
        for (int i = 0; i < 24; i++){
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        while(quotationRepository.findById(sb.toString()) != null) {
            for (int i = 0; i < 24; i++){
                int index = (int)(AlphaNumericString.length() * Math.random());
                sb.append(AlphaNumericString.charAt(index));
            }
        }
        quotation.setId(sb.toString());
        quotationRepository.save(quotation);
        return quotation;
    }

    public List<Quotation> getAllQuotations(){
        return quotationRepository.findAll();
    }

    public void updateQuotation(String id, Quotation quotation){
        quotation.setId(id);
        quotationRepository.save(quotation);
    }

    public void deleteQuotation(String id){
        quotationRepository.deleteById(id);
    }

    public Optional<Quotation> getQuotation(String id){
        return quotationRepository.findById(id);
    }
}
