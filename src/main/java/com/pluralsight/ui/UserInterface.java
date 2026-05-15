package com.pluralsight.ui;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface
{
    private static Scanner scanner = new Scanner(System.in);

    public static int getHomeScreenSelection(Dealership dealership)
    {
        System.out.println();
        System.out.println("Welcome to " + dealership.getName());
        System.out.println(dealership.getAddress());
        System.out.println(dealership.getPhone());
        System.out.println("-------------------------------");
        System.out.println();

        System.out.println("1. Show all Vehicles");
        System.out.println("2. Search by Price");
        System.out.println("3. Search by Year");
        System.out.println("4. Search by Color");
        System.out.println("5. Search by Mileage");
        System.out.println("6. Search by Vehicle Types");
        System.out.println(ColorCodes.RED + "7. Add a vehicle" + ColorCodes.RESET);
        System.out.println("0. Exit");

        System.out.println();
        return getUserInputInt("Select an option: ");
    }

    public static void endApplication()
    {
        System.out.println();
        System.out.println("Good bye");
        System.out.println();
    }

    public static void displayVehicles(ArrayList<Vehicle> vehicles)
    {
        for(Vehicle vehicle : vehicles)
        {
            displayVehicle(vehicle);
        }

        System.out.println("Press ENTER to continue");
        scanner.nextLine();
    }

    private static void displayVehicle(Vehicle vehicle)
    {
        System.out.printf("%-10d %-6d %-15s %-20s %-10s %-15s %-15d %.2f\n",
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice()
        );
    }

    public static void displayMessage(String message)
    {
        System.out.println(message);
    }

    public static String getUserInput(String message)
    {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int getUserInputInt(String message)
    {
        return Integer.parseInt(getUserInput(message));
    }

    public static double getUserInputDouble(String message)
    {
        return Double.parseDouble(getUserInput(message));
    }
}
