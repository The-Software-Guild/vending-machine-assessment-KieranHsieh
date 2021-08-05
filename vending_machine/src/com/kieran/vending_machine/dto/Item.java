package com.kieran.vending_machine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mutable representation of a Vending Machine item
 */
public class Item  {
    /**
     * The name of the item
     */
    private String name;
    /**
     * The cost of the item in dollars
     */
    private BigDecimal cost;
    /**
     * The number of the item in stock
     */
    private long stock;

    /**
     * Gets the name of the item
     * @return The name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the cost of the item
     * @return The cost of the item
     */
    public BigDecimal getCost() {
        return this.cost;
    }

    /**
     * Gets the number of the item in stock
     * @return The number of the item in stock
     */
    public long getStock() {
        return this.stock;
    }

    /**
     * Sets the name of the item
     * @param newName The new name of the item
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the cost of the item
     * @param newCost The new cost of the item
     */
    public void setCost(BigDecimal newCost) {
        this.cost = newCost;
    }

    /**
     * Sets the amount of the item in stock
     * @param stock The new amount of the item in stock
     */
    public void setStock(long stock) {
        this.stock = stock;
    }

    /**
     * Generates a hash code for the Item
     * @return The item as an integer
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.cost);
        hash = 89 * hash + Objects.hashCode(this.stock);
        return hash;
    }

    /**
     * Tests if an item is equal to another item
     * @param obj The item to compare with
     * @return If the item is equal to another item
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Item)) {
            return false;
        }
        Item oItem = (Item)obj;
        return this.name.equals(oItem.name) && this.cost.equals(oItem.cost) && this.stock == oItem.stock;
    }

    /**
     * Converts the item to a string
     * @return The name of the item
     */
    @Override
    public String toString() {
        return this.name;
    }
}
