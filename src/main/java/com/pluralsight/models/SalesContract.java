package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private final double salesTaxAmount;
    private final double recordingFee;
    private double processingFee;
    private boolean isFinance;
    private double price;

    public SalesContract(LocalDate date, String customerName, String email, Vehicle vehicle,
                         double salesTaxAmount, double recordingFee, boolean isFinance) {
        super(date, customerName, email, vehicle);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.isFinance = isFinance;
    }

    @Override
    public double getTotalPrice(Vehicle vehicle) {
        processingFee = (vehicle.getPrice() < 10000) ? 295 : 495;

        this.price = salesTaxAmount + recordingFee + processingFee + vehicle.getPrice();
        return price;
    }

    @Override
    public double getTotalMonthlyPayment(Vehicle vehicle) {
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

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }
}