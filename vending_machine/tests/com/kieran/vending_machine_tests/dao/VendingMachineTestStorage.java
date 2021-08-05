package com.kieran.vending_machine_tests.dao;

import com.kieran.vending_machine.dao.VendingMachineStorage;
import com.kieran.vending_machine.dto.Item;

import java.util.Collection;

public class VendingMachineTestStorage extends VendingMachineStorage {
    public VendingMachineTestStorage() {
        super();
    }
    public VendingMachineTestStorage(Collection<Item> items) {
        super(items);
    }
    @Override
    public boolean save() {
        return true;
    }
    @Override
    public boolean load() {
        return true;
    }
}

