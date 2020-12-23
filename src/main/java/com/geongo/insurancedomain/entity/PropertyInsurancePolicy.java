package com.geongo.insurancedomain.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "property_insurance_policy")
public class PropertyInsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfInsurance type;
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> properties;

    {
        properties = new ArrayList<>();
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date endDate;
    private double insurancePayment;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfSigning;

    public PropertyInsurancePolicy(TypeOfInsurance type, User user, Date startDate, Date endDate,
                                   double insurancePayment, Date dateOfSigning) {
        this.type = type;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insurancePayment = insurancePayment;

        this.dateOfSigning = dateOfSigning;
    }

    public PropertyInsurancePolicy() {
    }

    public void addProperty(Property property){
        properties.add(property);
        property.setPolicy(this);
    }

    public void  removeProperty(Property property){
        properties.remove(property);
        property.setPolicy(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfInsurance getType() {
        return type;
    }

    public void setType(TypeOfInsurance type) {
        this.type = type;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getInsurancePayment() {
        return insurancePayment;
    }

    public void setInsurancePayment(double insurancePayment) {
        this.insurancePayment = insurancePayment;
    }

    public Date getDateOfSigning() {
        return dateOfSigning;
    }

    public void setDateOfSigning(Date dateOfSigning) {
        this.dateOfSigning = dateOfSigning;
    }
}
