package com.kieran.vending_machine.io;

import java.util.function.Predicate;

/**
 * An interface for receiving and displaying messages to and from the user
 */
public interface IUserIOHandler {
    /**
     * Displays a message using to the user.
     * @param message The message to display
     */
    void displayMessage(String message);

    /**
     * Retrieves string input from the user
     * @return The inputted string
     */
    String getStringInput();

    /**
     * Retrieves String input from the user after prompting with message
     * @param prompt The message to prompt with
     * @return The inputted String
     */
    String getStringInput(String prompt);

    /**
     * Retrieves and validates string input from the user after prompting with a message.
     * Upon invalid input being passed, the prompt is displayed again, prefixed with "Invalid Input. "
     * @param prompt The message to prompt with
     * @param validator The validation function called to determine if the input was valid
     * @return The inputted String
     */
    String getStringInput(String prompt, Predicate<String> validator);

    /**
     * Retrieves number input from the user
     * @return The inputted number
     */
    Number getNumberInput();

    /**
     * Retrieves Number input from the user after prompting with message
     * @param prompt The message to prompt with
     * @return The inputted Number
     */
    Number getNumberInput(String prompt);

    /**
     * Retrieves and validates Number input from the user after prompting with a message.
     * Upon invalid input being passed, the prompt is displayed again, prefixed with "Invalid Input. "
     * @param prompt The message to prompt with
     * @param validator The validation function called to determine if the input was valid
     * @return The inputted Number
     */
    Number getNumberInput(String prompt, Predicate<Number> validator);
}
