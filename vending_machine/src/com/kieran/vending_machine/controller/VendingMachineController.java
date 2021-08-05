package com.kieran.vending_machine.controller;

import com.kieran.vending_machine.dao.ItemNotFoundException;
import com.kieran.vending_machine.dao.VendingMachinePersistenceException;
import com.kieran.vending_machine.dto.Change;
import com.kieran.vending_machine.dto.User;
import com.kieran.vending_machine.service.InsufficientFundsException;
import com.kieran.vending_machine.service.NoItemInventoryException;
import com.kieran.vending_machine.service.VendingMachineService;
import com.kieran.vending_machine.view.EMenuSelection;
import com.kieran.vending_machine.view.VendingMachineView;

import java.math.BigDecimal;

/**
 * The application controller for the Vending Machine
 */
public class VendingMachineController {
    /**
     * The view used by the vending machine
     */
    private VendingMachineView view;
    /**
     * The vending machine's service layer
     */
    private VendingMachineService service;
    /**
     * The vending machine's current user
     */
    private User user;

    /**
     * Constructs a new VendingMachineController
     * @param view The view for the vending machine to use
     * @param service The service for the vending machine to use
     */
    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
        this.user = new User();
    }

    /**
     * Launch the vending machine application
     */
    public void run() {
        // Load the vending machine stock
        try {
            service.load();
        }
        catch(VendingMachinePersistenceException e) {
            view.displayMessage("Failed to load item inventory: " + e.getMessage());
        }

        // Display the items
        view.displayItems(service.getItems());

        // Get user's inputted options
        boolean finished = false;
        while(!finished) {
            // Get the user's selection
            EMenuSelection selection = view.getMenuSelections();
            switch(selection) {
                case Buy:
                    processBuyCommand();
                    view.displayItems(service.getItems());
                    break;
                case Exit:
                    finished = true;
                    break;
                case NOOP:
                    break;
            }
        }
        try {
            service.save();
        }
        catch(VendingMachinePersistenceException e) {
            view.displayMessage("Failed to save item inventory: " + e.getMessage());
        }
    }

    /**
     * Processes the buy command
     */
    private void processBuyCommand() {
        // Ask for inputted money
        BigDecimal userWallet = view.getUserWallet();
        user.setWallet(userWallet);

        // Get item to buy
        String itemName = view.getItemName();

        try {
            // We do not need to add the change back to the user, since the service does this for us
            // already
            Change change = service.buyItem(user, itemName);
            view.displayChange(change);
        }
        catch(ItemNotFoundException | InsufficientFundsException | NoItemInventoryException e) {
            view.displayMessage(e.getMessage());
        }
    }
}
