package com.pluralsight.models;

import com.pluralsight.Enums.ContractType;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    private final double expectedEndingValue;
    private final double leaseFee;

    public LeaseContract(LocalDate date, String customerName, String email, Vehicle vehicle,
                         ContractType contractType, double expectedEndingValue, double leaseFee) {
        super(date, customerName, email, vehicle, contractType);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;

    }

    @Override
    public double getTotalPrice(Vehicle vehicle) {
        double price = vehicle.getPrice() * (leaseFee + expectedEndingValue);
        return vehicle.getPrice() - price;
    }

    @Override
    public double getTotalMonthlyPayment(Vehicle vehicle) {
        double price = getTotalPrice(vehicle);
        int months = 36;

        double monthlyRate = .04 / 12;

        return price * (monthlyRate * (Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1));
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }
}
