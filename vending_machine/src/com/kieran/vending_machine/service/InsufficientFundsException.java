package com.kieran.vending_machine.service;

import com.kieran.vending_machine.dto.Item;

import java.math.BigDecimal;

/**
 * An exception thrown when the user attempts to purchase an item when they do not have enough money
 */
public class InsufficientFundsException extends Exception {
    /**
     * Constructs a new InsufficientFundsException with the message
     *
     * You cannot buy itemName, which costs itemCost with only userWallet
     *
     * @param userWallet The amount of money owned by the user
     * @param item The price of the item
     */
    public InsufficientFundsException(BigDecimal userWallet, Item item) {
        super(String.format("You cannot buy %s, which costs $%.2f with only $%.2f", item.getName(), item.getCost(), userWallet));
    }
}
