package com.pluralsight.models;

import com.pluralsight.Enums.ContractType;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private final double salesTaxAmount;
    private final double recordingFee;
    private final double processingFee;
    private boolean isFinance;
    private double price;

    public SalesContract(LocalDate date, String customerName, String email, Vehicle vehicle, ContractType contractType,
                         double salesTaxAmount, double recordingFee, boolean isFinance) {
        super(date, customerName, email, vehicle, contractType);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.isFinance = isFinance;
        this.processingFee = super.getVehicle().getPrice() < 10000 ? 295 : 495;
    }

    @Override
    public double getTotalPrice() {
        Vehicle vehicle = super.getVehicle();

        System.out.println("Processing fee: " + processingFee);
        this.price = salesTaxAmount + recordingFee + processingFee + vehicle.getPrice();

        return price;
    }

    @Override
    public double getTotalMonthlyPayment() {
        Vehicle vehicle = super.getVehicle();

        if(isFinance) {
            double annualRate = vehicle.getPrice() > 10000 ? .0425 : .0525;
            int months = vehicle.getPrice() > 10000 ? 48 : 24;

            double monthlyRate = annualRate / 12;

            return price * (monthlyRate * (Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1));
        }

        return 0;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }
}