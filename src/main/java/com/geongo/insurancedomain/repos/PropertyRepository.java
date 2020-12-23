package com.geongo.insurancedomain.repos;

import com.geongo.insurancedomain.entity.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {
}
