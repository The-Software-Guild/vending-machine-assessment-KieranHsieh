package com.kieran.vending_machine.service;

import com.kieran.vending_machine.dto.Item;

/**
 * An exception thrown when the user attempts to purchase an item that is out of stock
 */
public class NoItemInventoryException extends Exception {
    /**
     * Constructs a new NoItemInventoryException with the message
     *
     * You cannot purchase itemName because it is out of stock!
     *
     * @param item The item that the user attempted to purchase
     */
    public NoItemInventoryException(Item item) {
        super(String.format("You cannot purchase %s because it is out of stock!", item.getName()));
    }
}
