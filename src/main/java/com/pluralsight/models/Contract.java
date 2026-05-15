package com.pluralsight.models;

import java.time.LocalDate;

public abstract class Contract {

    private LocalDate date;
    private String customerName;
    private String email;
    private Vehicle vehicle;


    public Contract(LocalDate date, String customerName, String email, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.email = email;
        this.vehicle = vehicle;
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

    public abstract double getTotalPrice(Vehicle vehicle);

    public abstract double getTotalMonthlyPayment(Vehicle vehicle);
}
