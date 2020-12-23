package com.geongo.insurancedomain.entity;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private double insuranceSum;
    @ManyToOne(fetch = FetchType.LAZY)
    private PropertyInsurancePolicy policy;

    public Property(String name, double insuranceSum) {
        this.name = name;
        this.insuranceSum = insuranceSum;
    }

    public Property() {
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

    public double getInsuranceSum() {
        return insuranceSum;
    }

    public void setInsuranceSum(double insuranceSum) {
        this.insuranceSum = insuranceSum;
    }

    public PropertyInsurancePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(PropertyInsurancePolicy policy) {
        this.policy = policy;
    }
}
