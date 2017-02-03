package com.epam.oop.controller;

import com.epam.oop.controller.command.Command;
import com.epam.oop.controller.command.exception.CommandExecutionException;
import com.epam.oop.controller.exception.ControllerException;

/**
 * Implementation of Controller layer.
 *
 * @author Uladzislau Seuruk.
 */
public class Controller {
    /**
     * Repository with known commands.
     */
    private final CommandProvider provider = new CommandProvider();
    /**
     * Symbol that separates command form parameters.
     */
    private static final char DELIMITER = ' ';

    /**
     * Executes received command.
     *
     * @param request <tt>String</tt> with command and its parameters.
     * @return <tt>String</tt> with response.
     * @throws ControllerException if there were some troubles occurred while command execution.
     */
    public String executeCommand(String request) throws ControllerException {
        request = request.trim();
        int index = request.indexOf(DELIMITER);
        if (index != -1) {
            String commandName = request.substring(0, index);
            Command command = provider.getCommand(commandName);
            try {
                return command.execute(request.substring(request.indexOf(DELIMITER) + 1));
            } catch (CommandExecutionException cee) {
                throw new ControllerException(cee);
            }
        }
        Command command = provider.getCommand(request);
        try {
            return command.execute("");
        } catch (CommandExecutionException cee) {
            throw new ControllerException(cee);
        }
    }
}