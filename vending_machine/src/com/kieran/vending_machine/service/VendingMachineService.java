package com.kieran.vending_machine.service;

import com.kieran.vending_machine.dao.ItemNotFoundException;
import com.kieran.vending_machine.dao.VendingMachineStorage;
import com.kieran.vending_machine.dao.VendingMachinePersistenceException;
import com.kieran.vending_machine.dto.Change;
import com.kieran.vending_machine.dto.Item;
import com.kieran.vending_machine.dto.User;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * An abstraction for a service layer used by the Vending Machine Controller
 */
public abstract class VendingMachineService {
    /**
     * The storage used by the service
     */
    protected VendingMachineStorage dao;

    /**
     * Constructs a new VendingMachineService with an assigned storage
     * @param dao The storage that is used by the service
     */
    public VendingMachineService(VendingMachineStorage dao) {
        this.dao = dao;
    }

    /**
     * Loads the VendingMachineService's data from it's storage's domain
     * @throws VendingMachinePersistenceException thrown when loading the data fails
     */
    public void load() throws VendingMachinePersistenceException {
        dao.load();
    }

    /**
     * Processes the event in which the User purchases an Item
     * @param user The user purchasing the item
     * @param itemName The name of the item being purchased
     * @return The change given back to the user. This is the amount that was not removed from
     * the user's wallet, but was left over from the initial amount that the user put in
     * @throws ItemNotFoundException thrown when the item could not be found
     * @throws InsufficientFundsException thrown when the user did not have enough money to purchase the item
     * @throws NoItemInventoryException thrown when the item was out of stock
     * @throws ItemNotFoundException thrown when the item could not be found
     */
    public abstract Change buyItem(User user, String itemName) throws InsufficientFundsException, NoItemInventoryException, ItemNotFoundException;

    /**
     * Retrieves all items from the service's storage
     * @return ALl items in the service's storage
     */
    public Collection<Item> getItems() {
        return dao.getItems();
    }

    /**
     * Retrieves all items that satisfy a condition from the service's storage
     * @param filter The condition that items must satisfy
     * @return ALl items in the service's storage that satisfy the given condition
     */
    public Collection<Item> getItems(Predicate<Item> filter) {
        return dao.getItems(filter);
    }

    /**
     * Saves the vending machine's storage to it's domain
     * @throws VendingMachinePersistenceException thrown when an error occurs when saving the storage
     */
    public void save() throws VendingMachinePersistenceException {
        dao.save();
    }
}
