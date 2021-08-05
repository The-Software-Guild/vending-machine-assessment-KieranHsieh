package com.kieran.vending_machine_tests.service;

import com.kieran.vending_machine.dao.ItemNotFoundException;
import com.kieran.vending_machine.io.VendingMachineNullLogger;
import com.kieran.vending_machine.dto.Item;
import com.kieran.vending_machine.dto.User;
import com.kieran.vending_machine.service.InsufficientFundsException;
import com.kieran.vending_machine.service.NoItemInventoryException;
import com.kieran.vending_machine.service.VendingMachineDefaultService;
import com.kieran.vending_machine.service.VendingMachineService;
import com.kieran.vending_machine_tests.dao.VendingMachineTestStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDefaultServiceTest {
    private VendingMachineTestStorage testData;

    @BeforeEach
    void initTestData() {
        ArrayList<Item> testItems = new ArrayList<>();
        Item item1 = new Item();
        item1.setName("TestOne");
        item1.setCost(new BigDecimal(1.00));
        item1.setStock(999);
        Item item2 = new Item();
        item2.setName("TestTwo");
        item2.setCost(new BigDecimal(0.00));
        item2.setStock(0);
        Item item3 = new Item();
        item3.setName("TestThree");
        item3.setCost(BigDecimal.ZERO);
        item3.setStock(1);
        testItems.add(item1);
        testItems.add(item2);
        testItems.add(item3);
        testData = new VendingMachineTestStorage(testItems);
    }

    @Test
    void buyItem() {
        VendingMachineService service = new VendingMachineDefaultService(testData, new VendingMachineNullLogger());

        // Init users
        User richUser = new User();
        User brokeUser = new User(BigDecimal.ZERO);

        // Add money to rich user
        richUser.addToWallet(new BigDecimal(9999));

        // Assertions
        assertDoesNotThrow(() -> service.buyItem(richUser, "TestOne"));
        assertThrows(ItemNotFoundException.class, () -> service.buyItem(richUser, "DNE Item"));
        assertEquals(BigDecimal.valueOf(9998), richUser.getWallet());
        assertThrows(NoItemInventoryException.class, () -> service.buyItem(richUser, "TestTwo"));
        assertEquals(BigDecimal.valueOf(9998), richUser.getWallet());
        assertThrows(InsufficientFundsException.class, () -> service.buyItem(brokeUser, "TestOne"));
        assertEquals(BigDecimal.valueOf(0), brokeUser.getWallet());
        assertDoesNotThrow(() -> service.buyItem(brokeUser, "TestThree"));
        assertEquals(BigDecimal.valueOf(0), brokeUser.getWallet());
        assertThrows(NoItemInventoryException.class, () -> service.buyItem(brokeUser, "TestThree"));
    }

    @Test
    void buyItemReduceStockTest() {
        VendingMachineService service = new VendingMachineDefaultService(testData, new VendingMachineNullLogger());
        User testUser = new User(BigDecimal.valueOf(999));
        assertDoesNotThrow(() -> service.buyItem(testUser, "TestOne"));
        var items = service.getItems(item -> item.getName().equals("TestOne"));
        for(Item i : items) {
            assertEquals(998, i.getStock());
        }
    }
}