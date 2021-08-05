package com.kieran.vending_machine.io;

/**
 * A VendingMachineLogger implementation that
 * redirects log messages to the console
 */
public class VendingMachineConsoleLogger extends VendingMachineLogger {
    /**
     * Logs a message to the implementors assigned sink
     * @param message The message to log
     * @throws LoggerException thrown when the message failed to be logged
     */
    @Override
    public void logMessage(String message) throws LoggerException {
        System.out.println(getLocalDateTimeString() + " " + message);
    }
}
