package com.kieran.vending_machine.dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * An immutable representation of change in
 * quarters, dimes, nickles, and pennies
 */
public class Change {
    /**
     * The number of pennies
     */
    private long pennyCount;
    /**
     * The number of nickles
     */
    private long nickleCount;
    /**
     * The number of dimes
     */
    private long dimeCount;
    /**
     * The number of quarters
     */
    private long quarterCount;

    /**
     * Creates a change object by calculating the number of
     * quarters, dimes, nickles, and pennies in the provided dollar
     * amount
     * @param dollarAmount The amount of change in dollars
     */
    public Change(BigDecimal dollarAmount) {
        // Convert to number of pennies
        BigDecimal totalPennies = dollarAmount.multiply(BigDecimal.valueOf(100.0), MathContext.DECIMAL64);

        // Calculate number of quarters
        quarterCount = totalPennies.divide(BigDecimal.valueOf(ECoin.QUARTER.amount), RoundingMode.FLOOR).longValue();
        BigDecimal penniesRemaining = totalPennies.remainder(BigDecimal.valueOf(ECoin.QUARTER.amount));

        // Calculate number of dimes
        dimeCount = penniesRemaining.divide(BigDecimal.valueOf(ECoin.DIME.amount), RoundingMode.FLOOR).longValue();
        penniesRemaining = penniesRemaining.remainder(BigDecimal.valueOf(ECoin.DIME.amount));

        // Calculate number of nickles
        nickleCount = penniesRemaining.divide(BigDecimal.valueOf(ECoin.NICKLE.amount), RoundingMode.FLOOR).longValue();
        penniesRemaining = penniesRemaining.remainder(BigDecimal.valueOf(ECoin.NICKLE.amount));

        // Calculate number of pennies
        pennyCount = penniesRemaining.divide(BigDecimal.valueOf(ECoin.PENNY.amount), RoundingMode.FLOOR).longValue();
    }

    /**
     * Gets the number of quarters used by the Change
     * @return The number of quarters used
     */
    public long getQuarterCount() {
        return this.quarterCount;
    }
    /**
     * Gets the number of dimes used by the Change
     * @return The number of dimes used
     */
    public long getDimeCount() {
        return this.dimeCount;
    }
    /**
     * Gets the number of nickles used by the Change
     * @return The number of nickles used
     */
    public long getNickleCount() {
        return this.nickleCount;
    }
    /**
     * Gets the number of pennies used by the Change
     * @return The number of pennies used
     */
    public long getPennyCount() {
        return this.pennyCount;
    }

    /**
     * Gets the total amount of money in the Change object in dollars
     * @return The amount of money in the change object as dollars
     */
    public BigDecimal getDollarAmount() {
        BigDecimal amount = new BigDecimal(0.0, MathContext.DECIMAL64);
        amount = amount.add(new BigDecimal(getPennyCount()).multiply(BigDecimal.valueOf(ECoin.PENNY.amount)));
        amount = amount.add(new BigDecimal(getNickleCount()).multiply(BigDecimal.valueOf(ECoin.NICKLE.amount)));
        amount = amount.add(new BigDecimal(getDimeCount()).multiply(BigDecimal.valueOf(ECoin.DIME.amount)));
        amount = amount.add(new BigDecimal(getQuarterCount()).multiply(BigDecimal.valueOf(ECoin.QUARTER.amount)));
        amount = amount.divide(BigDecimal.valueOf(100.0), 2, RoundingMode.HALF_DOWN);
        return amount;
    }
}
