package com.geongo.insurancedomain.services;

import com.geongo.insurancedomain.entity.Property;
import com.geongo.insurancedomain.repos.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public boolean saveProperty(Property property){

        propertyRepository.save(property);

        return true;
    }
}
