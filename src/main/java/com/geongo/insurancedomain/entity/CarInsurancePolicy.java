package com.geongo.insurancedomain.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "car_insurance_policy")
public class CarInsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfInsurance type;
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> allowedToDrive;

    {
        allowedToDrive = new ArrayList<>();
    }
    private String carBrandAndModel;
    private double carCost;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date endDate;
    private String vehicleIdentificationNumber;
    private String vehicleNumber;
    private double insurancePayment;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfSigning;

    public CarInsurancePolicy(TypeOfInsurance type, String carBrandAndModel, User user,
                              Date startDate, Date endDate, String vehicleIdentificationNumber, String vehicleNumber, List<Person> allowedToDrive, double insurancePayment, Date dateOfSigning) {
        this.type = type;
        this.carBrandAndModel = carBrandAndModel;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.vehicleNumber = vehicleNumber;
        this.allowedToDrive = allowedToDrive;
        this.insurancePayment = insurancePayment;
        this.dateOfSigning = dateOfSigning;
    }

    public CarInsurancePolicy() {
    }

    public void addPerson(Person person){
        allowedToDrive.add(person);
        person.setPolicy(this);
    }

    public void  removePerson(Person person){
        allowedToDrive.remove(person);
        person.setPolicy(null);
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

    public String getCarBrandAndModel() {
        return carBrandAndModel;
    }

    public void setCarBrandAndModel(String carBrandAndModel) {
        this.carBrandAndModel = carBrandAndModel;
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

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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

    public List<Person> getAllowedToDrive() {
        return allowedToDrive;
    }

    public void setAllowedToDrive(List<Person> allowedToDrive) {
        this.allowedToDrive = allowedToDrive;
    }

    public double getCarCost() {
        return carCost;
    }

    public void setCarCost(double carCost) {
        this.carCost = carCost;
    }
}
