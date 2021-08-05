package com.kieran.vending_machine_tests.dao;

import com.kieran.vending_machine.dao.ItemNotFoundException;
import com.kieran.vending_machine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineStorageTest {
    private VendingMachineTestStorage testStructure;
    @BeforeEach
    void initTestStructure() {
        ArrayList<Item> testItems = new ArrayList<>();
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
    void initEmpty() {
        VendingMachineTestStorage test = new VendingMachineTestStorage();
        Item testItem = new Item();
        testItem.setName("Test Value");
        assertFalse(test.getItems().contains(testItem));
    }

    @Test
    void getItem() throws ItemNotFoundException {
        assertDoesNotThrow(() -> testStructure.getItem("TestOne"));
        assertThrows(ItemNotFoundException.class, () -> testStructure.getItem("Not An Item"));
        Item i = testStructure.getItem("TestOne");
        assertEquals("TestOne", i.getName());
        assertEquals(new BigDecimal(4.00), i.getCost());
        assertEquals(4, i.getStock());
    }

    @Test
    void getItems() {
        Collection<Item> items = testStructure.getItems();
        assertEquals(2, items.size());
    }

    @Test
    void getItemsFiltered() {
        Collection<Item> items = testStructure.getItems(item -> item.getName().equals("TestOne"));
        assertEquals(1, items.size());
    }

    @Test
    void addItem() {
        Item newItem = new Item();
        newItem.setName("NewItem");
        newItem.setStock(0);
        newItem.setCost(new BigDecimal(5));
        testStructure.addItem(newItem);
        var items = testStructure.getItems();
        assertTrue(items.contains(newItem));
    }
}