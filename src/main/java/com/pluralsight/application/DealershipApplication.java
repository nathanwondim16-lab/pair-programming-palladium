package com.pluralsight.application;

import com.pluralsight.Enums.ContractType;
import com.pluralsight.io.ContractFileManager;
import com.pluralsight.io.DealershipFileManager;
import com.pluralsight.models.*;
import com.pluralsight.Enums.Colors;
import com.pluralsight.ui.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class DealershipApplication
{


    Dealership dealership;

    public DealershipApplication(Dealership dealership)
    {
        this.dealership = dealership;
    }

    public void run()
    {
        while(true)
        {
            int choice = UserInterface.getHomeScreenSelection(dealership);

            switch (choice)
            {
                case 1:
                    displayAllVehicles();
                    break;
                case 2:
                    searchByPrice();
                    break;
                case 3:
                    searchByYear();
                    break;
                case 4:
                    searchByColor();
                    break;
                case 5:
                    searchByMileage();
                    break;
                case 6:
                    searchByVehicleType();
                    break;
                case 7:
                    addVehicle();
                    break;
                case 8:
                    sellVehicle();
                    break;
                case 9:
                    leaseVehicle();
                    break;
                case 0:
                    UserInterface.endApplication();
                    System.exit(0);
            }
        }
    }

    private void displayAllVehicles()
    {
        UserInterface.displayMessage("\nShow All Vehicles");
        UserInterface.displayMessage("------------------------------------");
        UserInterface.displayVehicles(dealership.getAllVehicles());
    }

    private void searchByPrice()
    {
        UserInterface.displayMessage("\nSearch by Price");
        UserInterface.displayMessage("------------------------------------");
        double min = UserInterface.getUserInputDouble("Minimum Price: ");
        double max = UserInterface.getUserInputDouble("Maximum Price: ");

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        UserInterface.displayMessage("------------------------------------");
        UserInterface.displayVehicles(vehicles);

    }

    private void searchByYear()
    {
        // get min and max year from user
        UserInterface.displayMessage("\nSearch by year");
        UserInterface.displayMessage("--------------------------");
        int minYear = UserInterface.getUserInputInt("Minimum Year: ");
        int maxYear = UserInterface.getUserInputInt("Maximum Year: ");

        // search for vehicle by year
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);

        // display the vehicles
        UserInterface.displayVehicles(vehicles);
    }

    private void searchByColor()
    {
        UserInterface.displayMessage("\nSearch by color");
        UserInterface.displayMessage("------------------------------------");

        String color = UserInterface.getUserInput("Color: ");

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);

        UserInterface.displayVehicles(vehicles);
    }

    private void searchByMileage()
    {
        UserInterface.displayMessage("\nSearch by mileage");
        UserInterface.displayMessage("------------------------------------");

        int minMileage = UserInterface.getUserInputInt("Minimum Mileage: ");
        int maxMileage = UserInterface.getUserInputInt("Maximum Mileage: ");

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);

        UserInterface.displayVehicles(vehicles);
    }

    private void searchByVehicleType()
    {
        UserInterface.displayMessage("\nSearch by vehicle type");
        UserInterface.displayMessage("------------------------------------");

        String vehicleType = UserInterface.getUserInput("Vehicle Type: ");

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);

        UserInterface.displayVehicles(vehicles);
    }

    private void addVehicle()
    {

        int vin = UserInterface.getUserInputInt("\n\nEnter vehicle VIN number: ");
        int year = UserInterface.getUserInputInt("Enter vehicle year: ");
        String make = UserInterface.getUserInput("Enter Make: ");
        String model = UserInterface.getUserInput("Enter model: ");
        String vehicleType = UserInterface.getUserInput("Enter vehicle type: ");
        String color = UserInterface.getUserInput("Enter color: ");
        int odometer = UserInterface.getUserInputInt("Enter miles: ");
        double price = UserInterface.getUserInputDouble("Enter price: ");
        DealershipFileManager.addVehicle(new Vehicle(
                vin,
                year,
                make,
                model,
                vehicleType,
                color,
                odometer,
                price
                )
        );

        UserInterface.displayMessage(Colors.GREEN.colorize("\nVEHICLE ADDED"));
    }

    private void sellVehicle() {
        String customerName = UserInterface.getUserInput("Enter your name: ");
        String emailAddress = UserInterface.getUserInput("Enter your email: ");
        int VIN = UserInterface.getUserInputInt("Enter the Vehicle's Vin number");
        Vehicle vehicle = dealership.getVehicleByVin(VIN);

        String userChoice = UserInterface.getUserInput("Do you want to finance the vehicle?");

        SalesContract salesContract = new SalesContract(LocalDate.now(), customerName, emailAddress, vehicle,
                ContractType.SALE, .05, 100, userChoice.equalsIgnoreCase("yes"));

        UserInterface.displayMessage(String.valueOf(salesContract.getTotalPrice(vehicle)));
        UserInterface.displayMessage(String.valueOf(salesContract.getTotalMonthlyPayment(vehicle)));

        ContractFileManager.saveContract(salesContract);
    }

    private void leaseVehicle() {
        String customerName = UserInterface.getUserInput("\nEnter your name: ");
        String emailAddress = UserInterface.getUserInput("Enter your email: ");
        int VIN = UserInterface.getUserInputInt("Enter the Vehicle's Vin number: ");
        Vehicle vehicle = dealership.getVehicleByVin(VIN);

        LeaseContract leaseContract = new LeaseContract(LocalDate.now(), customerName, emailAddress,
                vehicle, ContractType.LEASE, .50, .07);

        ContractFileManager.saveContract(leaseContract);
    }
}
