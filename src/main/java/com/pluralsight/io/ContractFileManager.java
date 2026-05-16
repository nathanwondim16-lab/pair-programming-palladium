package com.pluralsight.io;

import com.pluralsight.models.Contract;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContractFileManager {

    private static final String FILENAME = "data/contracts.csv";


    public static void saveContract(Contract contract) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            Path path = Path.of(FILENAME);

            if(Files.exists(path) && Files.size(path) > 0) {
                writer.println();
            }

            writer.printf("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|",
                    contract.getContractType(),
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getEmail(),
                    contract.getVehicle().getVin(),
                    contract.getVehicle().getYear(),
                    contract.getVehicle().getMake(),
                    contract.getVehicle().getModel(),
                    contract.getVehicle().getVehicleType(),
                    contract.getVehicle().getColor(),
                    contract.getVehicle().getOdometer(),
                    contract.getVehicle().getPrice()
                    );

            if(contract instanceof SalesContract) {
                writer.printf("%.0f|%.0f|%.0f|%.2f|%s|%.2f",
                        ((SalesContract) contract).getSalesTaxAmount(),
                        ((SalesContract) contract).getRecordingFee(),
                        ((SalesContract) contract).getProcessingFee(),
                        contract.getTotalPrice(contract.getVehicle()),
                        ((SalesContract) contract).isFinance() ? "YES" : "NO",
                        contract.getTotalMonthlyPayment(contract.getVehicle())
                        );
            } else {
                writer.printf("%.0f|%.0f|%.2f|%.2f",
                        ((LeaseContract) contract).getExpectedEndingValue(),
                        ((LeaseContract) contract).getLeaseFee(),
                        contract.getTotalPrice(contract.getVehicle()),
                        contract.getTotalMonthlyPayment(contract.getVehicle())
                        );
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}