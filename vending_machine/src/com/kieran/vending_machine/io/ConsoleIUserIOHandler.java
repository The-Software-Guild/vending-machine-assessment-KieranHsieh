package com.kieran.vending_machine.io;

import com.kieran.vending_machine.util.IConverter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * A user i/o handler using the console
 */
public class ConsoleIUserIOHandler implements IUserIOHandler {
    /**
     * Displays a message using to the user.
     *
     * @param message The message to display
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Retrieves string input from the user
     *
     * @return The inputted string
     */
    @Override
    public String getStringInput() {
        return getStringInput(null);
    }

    /**
     * Retrieves String input from the user after prompting with message
     *
     * @param prompt The message to prompt with
     * @return The inputted String
     */
    @Override
    public String getStringInput(String prompt) {
        return getStringInput(prompt, input ->true);
    }

    /**
     * Retrieves and validates string input from the user after prompting with a message.
     * Upon invalid input being passed, the prompt is displayed again, prefixed with "Invalid Input. "
     *
     * @param prompt    The message to prompt with
     * @param validator The validation function called to determine if the input was valid
     * @return The inputted String
     */
    @Override
    public String getStringInput(String prompt, Predicate<String> validator) {
        return getUserInput(prompt, validator, input -> input);
    }

    /**
     * Retrieves number input from the user
     *
     * @return The inputted number
     */
    @Override
    public Number getNumberInput() {
        return getNumberInput(null);
    }

    /**
     * Retrieves Number input from the user after prompting with message
     *
     * @param prompt The message to prompt with
     * @return The inputted Number
     */
    @Override
    public Number getNumberInput(String prompt) {
        return getNumberInput(prompt, input -> true);
    }

    /**
     * Retrieves and validates Number input from the user after prompting with a message.
     * Upon invalid input being passed, the prompt is displayed again, prefixed with "Invalid Input. "
     *
     * @param prompt    The message to prompt with
     * @param validator The validation function called to determine if the input was valid
     * @return The inputted Number
     */
    @Override
    public Number getNumberInput(String prompt, Predicate<Number> validator) {
        return getUserInput(prompt, validator, input -> {
            try {
                return NumberFormat.getInstance().parse(input);
            }
            catch(ParseException e) {
                return null;
            }
        });
    }

    /**
     * Attempts to retrieve user input, re prompting until the provided input is both not null and valid
     * @param prompt The message to prompt with
     * @param validator The function used to validate the input
     * @param converter The conversion function used to change String input into the expected type. Upon
     *                  invalid conversion, this function should return null.
     * @param <T> The type of input to retrieve
     * @return The user's input
     */
    private <T> T getUserInput(String prompt, Predicate<T> validator, IConverter<T, String> converter) {
        String prefix = "";
        while(true) {
            Scanner scanner = new Scanner(System.in);
            if(prompt != null) {
                System.out.print(prefix + prompt);
            }
            String strInput;
            try {
                strInput = scanner.nextLine();
            }
            catch(NoSuchElementException e) {
                System.out.println("User input exhausted");
                System.exit(1);
                return null;
            }
            T convertedInput = converter.convert(strInput);
            if(convertedInput != null && validator.test(convertedInput)) {
                return convertedInput;
            }
            else {
                prefix = "Invalid Input. ";
            }
        }
    }
}
