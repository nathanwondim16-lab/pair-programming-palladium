package com.pluralsight;

import com.pluralsight.application.DealershipApplication;
import com.pluralsight.models.Dealership;
import com.pluralsight.io.DealershipFileManager;

import java.io.IOException;

public class Main
{
    static void main()
    {
        try
        {
            Dealership dealership = DealershipFileManager.loadDealership();
            DealershipApplication app = new DealershipApplication(dealership);
            app.run();
        }
        catch(IOException e)
        {
            System.out.println("There was an error reading the data file.");
        }
    }
}
