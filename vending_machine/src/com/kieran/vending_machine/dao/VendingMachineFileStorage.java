package com.kieran.vending_machine.dao;

import com.kieran.vending_machine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineFileStorage extends VendingMachineStorage {
    /**
     * The delimiter used by the DAO
     */
    private static final String DELIMITER = "::";
    /**
     * The output file used by the DAO
     */
    private final String OUTPUT_FILE;

    /**
     * Constructs a new VendingMachineFileStorage object with an assigned output file
     * @param outputFile The file to save to and load from
     */
    public VendingMachineFileStorage(String outputFile) {
        super();
        this.OUTPUT_FILE = outputFile;
    }

    /**
     * Constructs a new VendingMachineFileStorage object with initialized data and an
     * assigned output file
     * @param items The data used to initialize the storage
     * @param outputFile The file that the storage will write to and read from
     */
    public VendingMachineFileStorage(Collection<Item> items, String outputFile) {
        super(items);
        this.OUTPUT_FILE = outputFile;
    }
    /**
     * Saves the stock currently held by the vending machine to the subclass's associated
     * domain
     *
     * @return If the items were saved successfully
     */
    @Override
    public boolean save() throws VendingMachinePersistenceException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(OUTPUT_FILE));
        }
        catch(IOException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        for(Item i : this.items.values()) {
            writer.println(marshallItem(i));
        }
        writer.flush();
        writer.close();
        return true;
    }

    /**
     * Loads the vending machine's stock from the subclass's associated domain
     *
     * @return If the items were successfully loaded
     */
    @Override
    public boolean load() throws VendingMachinePersistenceException {
        if(!new File(OUTPUT_FILE).exists()) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE));
                writer.flush();
                writer.close();
            }
            catch(IOException e) {
                throw new VendingMachinePersistenceException(e.getMessage());
            }
        }
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(OUTPUT_FILE)));
            String curLine;
            Item curItem;
            while(scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                curItem = unmarshallItem(curLine);
                this.items.put(curItem.getName(), curItem);
            }
            scanner.close();
        }
        catch(IOException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        return true;
    }

    /**
     * Converts an item in a string format into an Item object
     * @param itemStr The Item in a string format
     * @return The converted Item object
     */
    private Item unmarshallItem(String itemStr) {
        String[] itemTokens = itemStr.split(DELIMITER);
        Item output = new Item();
        output.setName(itemTokens[0]);
        output.setCost(new BigDecimal(itemTokens[1]));
        output.setStock(Long.parseLong(itemTokens[2]));
        return output;
    }

    /**
     * Converts an Item Object into a string format
     * @param item The item to convert
     * @return The Item in a string format
     */
    private String marshallItem(Item item) {
       return item.getName() + DELIMITER +
               item.getCost().toString() + DELIMITER +
               item.getStock();
    }
}
