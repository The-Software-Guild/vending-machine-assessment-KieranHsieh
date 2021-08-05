package com.kieran.vending_machine.dto;

/**
 * An enumeration providing the values of different coins in cents.
 * This should not be confused with an E-Coin, as this is an enumeration of a coin
 */
public enum ECoin {
    /**
     * One cent
     */
    PENNY(1),
    /**
     * Five cents
     */
    NICKLE(5),
    /**
     * Ten cents
     */
    DIME(10),
    /**
     * Twenty Five cents
     */
    QUARTER(25);
    /**
     * The amount of the ECoin in cents
     */
    public final int amount;

    /**
     * Constructs the ECoin with an amount in cents
     * @param amountInCents The value of the coin in cents
     */
    ECoin(int amountInCents) {
        this.amount = amountInCents;
    }
}
