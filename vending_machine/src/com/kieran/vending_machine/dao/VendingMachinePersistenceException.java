package com.kieran.vending_machine.dao;

/**
 * An exception thrown when the vending machine's persistence fails
 */
public class VendingMachinePersistenceException extends Exception {
    /**
     * Constructs a new VendingMachinePersistenceException with a given message
     * @param msg The message to attach to the exception
     */
    public VendingMachinePersistenceException(String msg) {
        super(msg);
    }
}
