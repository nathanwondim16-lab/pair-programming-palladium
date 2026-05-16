package com.pluralsight.models;

import com.pluralsight.Enums.ContractType;

import java.time.LocalDate;

public abstract class Contract {

    private LocalDate date;
    private String customerName;
    private String email;
    private Vehicle vehicle;
    private final ContractType contractType;


    public Contract(LocalDate date, String customerName, String email, Vehicle vehicle, ContractType contractType) {
        this.date = date;
        this.customerName = customerName;
        this.email = email;
        this.vehicle = vehicle;
        this.contractType = contractType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract double getTotalPrice();

    public abstract double getTotalMonthlyPayment();
}
