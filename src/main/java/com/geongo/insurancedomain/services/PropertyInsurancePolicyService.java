package com.geongo.insurancedomain.services;

import com.geongo.insurancedomain.entity.CarInsurancePolicy;
import com.geongo.insurancedomain.entity.PropertyInsurancePolicy;
import com.geongo.insurancedomain.repos.CarInsurancePolicyRepository;
import com.geongo.insurancedomain.repos.PropertyInsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class PropertyInsurancePolicyService {

    @Autowired
    private PropertyInsurancePolicyRepository propertyInsurancePolicyRepository;
    @Autowired
    private PropertyService propertyService;

    public boolean savePropertyInsurancePolicy(PropertyInsurancePolicy propertyInsurancePolicy){

        propertyInsurancePolicyRepository.save(propertyInsurancePolicy);


        return true;
    }
}
