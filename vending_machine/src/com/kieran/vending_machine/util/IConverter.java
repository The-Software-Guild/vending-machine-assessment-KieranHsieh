package com.kieran.vending_machine.util;

@FunctionalInterface
public interface IConverter<TO, FROM> {
    TO convert(FROM val);
}
