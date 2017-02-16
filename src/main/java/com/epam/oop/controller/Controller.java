package com.epam.oop.controller;

import com.epam.oop.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of Controller layer.
 *
 * @author Uladzislau Seuruk.
 */
public final class Controller {
    private static final Logger LOG = LogManager.getRootLogger();
    /**
     * Symbol that separates command form parameters.
     */
    private static final char DELIMITER = ' ';
    /**
     * Repository with known commands.
     */
    private final CommandProvider provider = new CommandProvider();
    /**
     * Instance of this class.
     */
    private static Controller instance = new Controller();

    private Controller() {}

    /**
     * Executes received command.
     *
     * @param request <tt>String</tt> with command and its parameters.
     * @return <tt>String</tt> with response.
     */
    public String executeCommand(String request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(request);
        }

        request = request.trim();
        int index = request.indexOf(DELIMITER);
        Command command;
        String params;

        if (index != -1) {
            String commandName = request.substring(0, index);
            command = provider.getCommand(commandName);
            params = request.substring(index + 1);
        } else {
            command = provider.getCommand(request);
            params = "";
        }

        String response = command.execute(params);
        if (LOG.isDebugEnabled()) {
            LOG.debug(response);
        }
        return response;
    }

    /**
     * Returns instance of this class.
     */
    public static Controller getInstance() {
        return instance;
    }
}