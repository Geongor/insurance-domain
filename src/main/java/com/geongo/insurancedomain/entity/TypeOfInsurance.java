package com.geongo.insurancedomain.entity;

import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "types_list")
public class TypeOfInsurance {
    @Id
    private Long id;
    private String name;
    private String description;
    private String insurance;


    public TypeOfInsurance() {
    }

    public TypeOfInsurance(Long id, String name, String description, String insurance) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.insurance = insurance;
    }

    public Double calculateInsurancePayment(List<Double> sum){

        double insurancePayment = 0.0;

        double insuranceSum = 0.0;
        for (Double iSum: sum) {
            if (iSum!=null)
                insuranceSum += iSum;
        }

        switch (this.name){

            case "AVG": {

                insurancePayment = insuranceSum / sum.size();

                break;
            }
            case "PART": {

                insurancePayment = insuranceSum * 0.3;

                break;
            }
        }

        return insurancePayment;
    }

    public Double calculateInsurancePayment(double carCost){

        double insurancePayment = 0.0;

        switch (this.name){

            case "CARREAL": {

                insurancePayment = carCost * 0.04;

                break;
            }
            case "CARPART": {

                insurancePayment = carCost * 0.02;

                break;
            }
        }

        return insurancePayment;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}
