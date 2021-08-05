package com.kieran.vending_machine.dao;

/**
 * An exception thrown when an item could not be located in the vending machine
 */
public class ItemNotFoundException extends Exception {
    /**
     * Constructs a new exception with the format
     * "Item itemName was not found"
     * @param itemName The name of the item that could not be found
     */
    public ItemNotFoundException(String itemName) {
        super(String.format("Item %s was not found", itemName));
    }
}
