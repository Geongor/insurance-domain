package com.geongo.insurancedomain.repos;

import com.geongo.insurancedomain.entity.CarInsurancePolicy;
import org.springframework.data.repository.CrudRepository;

public interface CarInsurancePolicyRepository extends CrudRepository<CarInsurancePolicy, Long> {
}
