package com.kieran.vending_machine.view;

import com.kieran.vending_machine.dto.Change;
import com.kieran.vending_machine.dto.Item;
import com.kieran.vending_machine.io.IUserIOHandler;

import java.math.BigDecimal;
import java.util.Collection;

public class VendingMachineView {
    private IUserIOHandler io;
    private static final EMenuSelection[] SELECTIONS = EMenuSelection.values();


    public VendingMachineView(IUserIOHandler io) {
        this.io = io;
    }

    public void displayMessage(String message) {
        io.displayMessage(message);
    }

    public EMenuSelection getMenuSelections() {
        // Build welcome string
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to the Vending Machine!\n");
        for(int i = 0; i < SELECTIONS.length-1; i ++) {
            builder.append(i+1)
                    .append(") ")
                    .append(SELECTIONS[i].toString())
                    .append("\n");
        }
        builder.append("Please make a selection: ");
        // Get input and convert to EMenuSelection
        int selection = io.getNumberInput(builder.toString(), input -> input.intValue() > 0 && input.intValue() < SELECTIONS.length).intValue();
        return SELECTIONS[selection-1];
    }

    public String getItemName() {
        return io.getStringInput("What is the name of the item? ");
    }

    public BigDecimal getUserWallet() {
        Number num = io.getNumberInput("How much money are you putting in? ");
        return BigDecimal.valueOf(num.doubleValue());
    }

    public void displayItems(Collection<Item> items) {
        // Item Name, Item Price, # in Stock
        String formatStr = "%-15s | %-6.2f | %-15d";
        String info = String.format("%-15s | %-6s | %-15s", "Name", "Price", "Number in Stock");
        StringBuilder builder = new StringBuilder();

        // Add in the info and banner
        builder.append(info)
                .append("\n")
                .append("=".repeat(info.length()))
                .append("\n");

        // List all items
        for(Item i : items) {
            builder.append(String.format(formatStr, i.getName(), i.getCost(), i.getStock()))
                .append("\n")
                .append("-".repeat(info.length()))
                .append("\n");
        }

        // Display
        io.displayMessage(builder.toString());
    }

    public void displayChange(Change change) {
        String changeStr = "Your Change: \n" +
                change.getQuarterCount() + " quarters\n" +
                change.getDimeCount() + " dimes\n" +
                change.getNickleCount() + " nickles\n" +
                change.getPennyCount() + " pennies";
        io.displayMessage(changeStr);
    }
}
