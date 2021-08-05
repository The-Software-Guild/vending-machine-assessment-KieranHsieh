package com.kieran.vending_machine.dao;

import com.kieran.vending_machine.dto.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An abstraction for common code used to store a
 * local copies of the vending machine stock
 */
public abstract class VendingMachineStorage {
    /**
     * The backing storage used by the VendingMachineStorage class
     */
    protected HashMap<String, Item> items = new HashMap<>();

    /**
     * Constructs a new, empty VendingMachineStorage
     */
    public VendingMachineStorage() {}

    /**
     * Constructs a new VendingMachineStorage with initialized data
     * @param items The items used to initialize the storage's data
     */
    public VendingMachineStorage(Collection<Item> items) {
        items.forEach(item -> this.items.put(item.getName(), item));
    }
    /**
     * Saves the data currently held by the vending machine to the subclass's associated
     * domain
     * @return If the items were saved successfully
     */
    public abstract boolean save() throws VendingMachinePersistenceException;

    /**
     * Loads the vending machine's data from the subclass's associated domain
     * @return If the items were successfully loaded
     */
    public abstract boolean load() throws VendingMachinePersistenceException;

    /**
     * Retrieves a particular item from the storage
     * @param itemName The name of the item to retrieve
     * @return The retrieved item
     * @throws ItemNotFoundException thrown when the item was not present in the storage
     */
    public Item getItem(String itemName) throws ItemNotFoundException {
        Item i = this.items.get(itemName);
        if(i == null) {
            throw new ItemNotFoundException(itemName);
        }
        return i;
    }

    /**
     * Gets all items in the vending machine's storage
     * @return A collection of all items in the vending machine's storage
     */
    public Collection<Item> getItems() {
        return getItems(input -> true);
    }

    /**
     * Gets all items that satisfy a condition from the vending machine's storage
     * @param filter The condition that items must satisfy in order to be returned
     * @return The items in the vending machine's storage that satisfied the given condition
     */
    public Collection<Item> getItems(Predicate<Item> filter) {
        return this.items.values().stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * Adds an item to the vending machine's storage
     * @param item The item to add to the storage
     */
    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }
}
