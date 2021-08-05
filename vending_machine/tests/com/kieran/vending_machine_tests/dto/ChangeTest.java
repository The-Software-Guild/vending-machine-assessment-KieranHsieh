package com.kieran.vending_machine_tests.dto;

import com.kieran.vending_machine.dto.Change;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ChangeTest {
    @org.junit.jupiter.api.Test
    void getQuarterCount() {
        Change changeEven = new Change(new BigDecimal(0.25));
        Change changeExtra = new Change(new BigDecimal(0.26));
        Change changeLess = new Change(new BigDecimal(0.24));
        Change zeroCase = new Change(new BigDecimal(0.00));

        assertEquals(1, changeEven.getQuarterCount());
        assertEquals(1, changeExtra.getQuarterCount());
        assertEquals(0, changeLess.getQuarterCount());
        assertEquals(0, zeroCase.getQuarterCount());
    }

    @org.junit.jupiter.api.Test
    void getDimeCount() {
        Change changeEven = new Change(new BigDecimal(0.10));
        Change changeExtra = new Change(new BigDecimal(0.11));
        Change changeLess = new Change(new BigDecimal(0.09));
        Change zeroCase = new Change(new BigDecimal(0.00));

        assertEquals(1, changeEven.getDimeCount());
        assertEquals(1, changeExtra.getDimeCount());
        assertEquals(0, changeLess.getDimeCount());
        assertEquals(0, zeroCase.getDimeCount());
    }

    @org.junit.jupiter.api.Test
    void getNickleCount() {
        Change changeEven = new Change(new BigDecimal(0.05));
        Change changeExtra = new Change(new BigDecimal(0.06));
        Change changeLess = new Change(new BigDecimal(0.04));
        Change zeroCase = new Change(new BigDecimal(0.00));

        assertEquals(1, changeEven.getNickleCount());
        assertEquals(1, changeExtra.getNickleCount());
        assertEquals(0, changeLess.getNickleCount());
        assertEquals(0, zeroCase.getNickleCount());
    }

    @org.junit.jupiter.api.Test
    void getPennyCount() {
        Change changeEven = new Change(new BigDecimal(0.01));
        Change changeExtra = new Change(new BigDecimal(0.06));
        Change changeLess = new Change(new BigDecimal(0.00));

        assertEquals(1, changeEven.getPennyCount());
        assertEquals(1, changeExtra.getPennyCount());
        assertEquals(0, changeLess.getPennyCount());
    }

    @Test
    void getDollarAmount() {
        Change noChange = new Change(new BigDecimal(0));
        Change someChange = new Change(new BigDecimal(0.25));

        assertEquals(BigDecimal.ZERO.doubleValue(), noChange.getDollarAmount().doubleValue());
        assertEquals(new BigDecimal(0.25).doubleValue(), someChange.getDollarAmount().doubleValue());
    }
}