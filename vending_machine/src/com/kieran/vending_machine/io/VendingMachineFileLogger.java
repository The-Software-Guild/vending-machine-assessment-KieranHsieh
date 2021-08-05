package com.kieran.vending_machine.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class VendingMachineFileLogger extends VendingMachineLogger {
    private final String OUTPUT_FILE;
    public VendingMachineFileLogger(String outputFile) {
        OUTPUT_FILE = outputFile;
    }
    /**
     * Logs a message to the implementors assigned sink
     * @param message The message to log
     * @throws LoggerException thrown when the message failed to be logged
     */
    @Override
    public void logMessage(String message) throws LoggerException {
        File outputFile = new File(OUTPUT_FILE);
        try {
            if(!outputFile.exists()) {
                new FileWriter(OUTPUT_FILE).close();
            }
            Files.write(Paths.get(OUTPUT_FILE), (getLocalDateTimeString() + " " + message + "\n").getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e) {
            throw new LoggerException("Failed to write message to file: " + e.getMessage());
        }

    }
}
