package com.epam.oop.controller.command.impl;

import com.epam.oop.controller.command.Command;
import com.epam.oop.service.exception.ServiceException;
import com.epam.oop.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements Command interface for add new news command.
 *
 * @author Uladzislau Seuruk.
 */
public class AddNews implements Command {
    private static final Logger LOG = LogManager.getRootLogger();
    /**
     * Name of command.
     */
    public static final String COMMAND_NAME = "ADD_NEWS";

    /**
     * @see Command#execute(String)
     */
    @Override
    public String execute(String params) {
        String response;
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            factory.getCatalogService().addNews(params);
            response = "News was successfully added.";
        } catch (ServiceException se) {
            LOG.error(se.getMessage(), se);
            response = "Error while news search.";
        }
        return response;
    }
}