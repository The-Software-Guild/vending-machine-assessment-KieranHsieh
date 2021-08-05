package com.kieran.vending_machine.dto;

import java.math.BigDecimal;

/**
 * An abstraction of a Vending Machine's user
 */
public class User {
    /**
     * The amount of money that the user currently owns
     */
    private BigDecimal wallet;

    /**
     * Constructs a new user with 0 money
     */
    public User() {
        this.wallet = BigDecimal.ZERO;
    }

    /**
     * Constructs a new user with a given amount of money
     * @param wallet The money to initialize the user with
     */
    public User(BigDecimal wallet) {
        this.wallet = wallet;
    }

    /**
     * Gets the current amount of money owned by the user
     * @return The current amount of money owned by the user
     */
    public BigDecimal getWallet() {
        return this.wallet;
    }

    /**
     * Sets the current amount of money owned by the user
     * @param wallet The new amount of money owned by the user
     */
    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }
    /**
     * Adds money to the user's wallet
     * @param amount The amount to add to the user's wallet
     */
    public void addToWallet(BigDecimal amount) {
        this.wallet = this.wallet.add(amount);
    }

    /**
     * Subtracts money from the user's wallet
     * @param amount The amount of money to remove from the user's wallet
     */
    public void subtractFromWallet(BigDecimal amount) {
        this.wallet = this.wallet.subtract(amount);
    }
}
