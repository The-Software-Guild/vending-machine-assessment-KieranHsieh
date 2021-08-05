package com.kieran.vending_machine;

import com.kieran.vending_machine.controller.VendingMachineController;
import com.kieran.vending_machine.io.VendingMachineFileLogger;
import com.kieran.vending_machine.dao.VendingMachineFileStorage;
import com.kieran.vending_machine.io.VendingMachineLogger;
import com.kieran.vending_machine.io.ConsoleIUserIOHandler;
import com.kieran.vending_machine.io.IUserIOHandler;
import com.kieran.vending_machine.service.VendingMachineDefaultService;
import com.kieran.vending_machine.service.VendingMachineService;
import com.kieran.vending_machine.view.VendingMachineView;

/**
 * The entry point for the Vending Machine application
 */
public class App {
    public static void main(String[] args) {
        // View initialization
        IUserIOHandler consoleIO = new ConsoleIUserIOHandler();
        VendingMachineView view = new VendingMachineView(consoleIO);

        // Service initialization
        VendingMachineFileStorage dao = new VendingMachineFileStorage("MyData.txt");
        VendingMachineLogger logger = new VendingMachineFileLogger("VendingMachineAudit.txt");
        VendingMachineService service = new VendingMachineDefaultService(dao, logger);

        //Controller initialization
        VendingMachineController controller = new VendingMachineController(view, service);

        // Start application
        controller.run();
    }
}
