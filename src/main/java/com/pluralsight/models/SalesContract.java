package com.pluralsight.models;

import com.pluralsight.Enums.ContractType;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private final double salesTaxAmount;
    private final double recordingFee;
    private final double processingFee;
    private boolean isFinance;

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
        double amountBeforeTax = super.getVehicle().getPrice() + recordingFee + processingFee;

        return amountBeforeTax + amountBeforeTax * salesTaxAmount;
    }

    @Override
    public double getTotalMonthlyPayment() {
        if(isFinance) {
            double monthlyRate = super.getVehicle().getPrice() > 10000 ? .0425 / 12 : .0525 / 12;
            int months = super.getVehicle().getPrice() > 10000 ? 48 : 24;

            return getTotalPrice() * (monthlyRate * (Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1));
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