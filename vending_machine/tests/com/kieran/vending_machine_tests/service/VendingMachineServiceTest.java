package com.kieran.vending_machine_tests.service;

import com.kieran.vending_machine.dto.Item;
import com.kieran.vending_machine_tests.dao.VendingMachineTestStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceTest {
    private VendingMachineTestStorage testStructure;
    private ArrayList<Item> testItems;
    @BeforeEach
    void initTestStructure() {
        testItems = new ArrayList<>();
        Item item1 = new Item();
        item1.setName("TestOne");
        item1.setCost(new BigDecimal(4.00));
        item1.setStock(4);
        Item item2 = new Item();
        item2.setName("TestTwo");
        item2.setCost(new BigDecimal(0.00));
        item2.setStock(0);
        testItems.add(item1);
        testItems.add(item2);
        testStructure = new VendingMachineTestStorage(testItems);
    }

    @Test
    void getItems() {
        Collection<Item> items = testStructure.getItems();

        for(Item i : testItems) {
            assertTrue(items.contains(i));
        }
    }

    @Test
    void getItemsFiltered() {
        Collection<Item> items = testStructure.getItems(item -> item.getName().equals("TestOne"));
        assertEquals(1, items.size());
        assertTrue(items.contains(testItems.get(0)));
    }
}