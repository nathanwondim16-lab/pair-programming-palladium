package com.pluralsight.models;

import com.pluralsight.io.DealershipFileManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Dealership
{
    private final String name;
    private final String address;
    private final String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone, ArrayList<Vehicle> inventory)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = inventory;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max)
    {
        return inventory.stream()
                .filter(v -> v.getPrice() >= min && v.getPrice() <= max)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model)
    {

//        ArrayList<Vehicle> vehicles = new ArrayList<>();
//
//        for (Vehicle v : inventory)
//        {
//            if(v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
//            {
//                vehicles.add(v);
//            }
//        }
//
//        return vehicles;

        // streams allow us to replace lines 49-59
        return inventory.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max)
    {
        return inventory.stream()
                .filter(v -> v.getYear() >= min && v.getYear() <= max)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color)
    {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max)
    {
        return inventory.stream()
                .filter(v -> v.getOdometer() >= min && v.getOdometer() <= max)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Vehicle> getVehiclesByType(String type)
    {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Vehicle> getAllVehicles()
    {
        return inventory;
    }

    public void removeVehicle(Vehicle vehicle)
    {
        inventory = inventory.stream()
                .filter(v -> v.getVin() != vehicle.getVin())
                .collect(Collectors.toCollection(ArrayList::new));

        saveDealership();
    }

    public void saveDealership() {
        DealershipFileManager.saveDealership(this);
    }
}
