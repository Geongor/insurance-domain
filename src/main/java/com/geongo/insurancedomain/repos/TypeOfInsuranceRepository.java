package com.geongo.insurancedomain.repos;

import com.geongo.insurancedomain.entity.TypeOfInsurance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeOfInsuranceRepository extends CrudRepository<TypeOfInsurance, Long> {
    List<TypeOfInsurance> findByInsurance(String insurance);
}
