package com.kieran.vending_machine.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstraction for a logger used by the Vending machine.
 *
 * Each implementation of this class will determine how the provided
 * messages get logged
 */
public abstract class VendingMachineLogger {
    /**
     * Logs a message to the implementors assigned sink
     * @param message The message to log
     * @throws LoggerException thrown when the message failed to be logged
     */
    public abstract void logMessage(String message) throws LoggerException;

    /**
     * Gets the local date and time as a string in the format:
     *
     * [mm/dd/yyyy] [hh:mm:ss]
     *
     * @return The local date and time as a formatted string
     */
    protected static String getLocalDateTimeString() {
        LocalDateTime curTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['MM/dd/yyyy']' '['hh:mm:ss']: '");
        return curTime.format(formatter);
    }
}
