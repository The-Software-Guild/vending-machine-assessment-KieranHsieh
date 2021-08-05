package com.kieran.vending_machine.io;

/**
 * An implementation of the VendingMachineLogger class that does not log any
 * messages
 */
public class VendingMachineNullLogger extends VendingMachineLogger {
    /**
     * Does not log any message
     * @param message Parameter is ignored
     * @throws LoggerException never thrown
     */
    @Override
    public void logMessage(String message) throws LoggerException { }
}
