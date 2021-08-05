package com.kieran.vending_machine.service;

import com.kieran.vending_machine.dao.ItemNotFoundException;
import com.kieran.vending_machine.io.LoggerException;
import com.kieran.vending_machine.io.VendingMachineLogger;
import com.kieran.vending_machine.dao.VendingMachineStorage;
import com.kieran.vending_machine.dto.Change;
import com.kieran.vending_machine.dto.Item;
import com.kieran.vending_machine.dto.User;

import java.math.BigDecimal;

/**
 * A vending machine service layer implementation that
 * logs messages after purchases or errors
 */
public class VendingMachineDefaultService extends VendingMachineService {
    /**
     * The logger used by the service
     */
    private VendingMachineLogger logger;

    /**
     * Constructs a new VendingMachineDefaultService with a given storage and logger
     * @param dao The storage used by the service
     * @param logger The logger used by the service
     */
    public VendingMachineDefaultService(VendingMachineStorage dao, VendingMachineLogger logger) {
        super(dao);
        this.logger = logger;
    }

    /**
     * Processes a buy order from the service, logging a message if an exception is thrown
     * or if the buy order was valid
     * @param user The user purchasing the item
     * @param itemName The name of the item being purchased
     * @return The change returned to the user
     * @throws ItemNotFoundException thrown when an item could not be located
     * @throws InsufficientFundsException thrown when user did not have enough money to purchase the item
     * @throws NoItemInventoryException thrown when the item was out of stock
     */
    @Override
    public Change buyItem(User user, String itemName) throws ItemNotFoundException, InsufficientFundsException, NoItemInventoryException {
        Item item = this.dao.getItem(itemName);

        // Check if the item is out of stock
        if(item.getStock() == 0) {
            // Log the event
            try {
                logger.logMessage(String.format("User attempted to purchase %s, but it was out of stock", itemName));
            }
            catch (LoggerException e) {
                System.err.println(e.getMessage());
            }
            throw new NoItemInventoryException(item);
        }

        // Get the user's current wallet
        BigDecimal oldWallet = user.getWallet();

        // Check if the price is too high
        if(item.getCost().compareTo(user.getWallet()) > 0) {
            // Log the event
            try {
                logger.logMessage(String.format("User attempted to purchase %s for $%.2f, but only had $%.2f", itemName, item.getCost(), oldWallet));
            }
            catch(LoggerException e) {
                System.err.println(e.getMessage());
            }
            throw new InsufficientFundsException(user.getWallet(), item);
        }

        // Log the vent
        try {
            logger.logMessage(String.format("User purchased %s for $%.2f.", itemName, item.getCost()));
        }
        catch(LoggerException e) {
            System.err.println(e.getMessage());
        }

        // Update the user's wallet
        user.subtractFromWallet(item.getCost());

        // Calculate the change
        BigDecimal diff = oldWallet.subtract(user.getWallet());
        item.setStock(item.getStock()-1);
        return new Change(oldWallet.subtract(diff));
    }
}
