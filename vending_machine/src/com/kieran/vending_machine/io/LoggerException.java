package com.kieran.vending_machine.io;

/**
 * An exception thrown when a logger failed to log a message
 * to it's assigned sink
 */
public class LoggerException extends Exception {
    /**
     * Constructs a new LoggerException with a message
     * @param msg The message attached to the exception
     */
    public LoggerException(String msg) {
        super(msg);
    }
}
