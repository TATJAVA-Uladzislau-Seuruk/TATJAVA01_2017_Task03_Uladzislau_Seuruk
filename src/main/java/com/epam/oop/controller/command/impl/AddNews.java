package com.epam.oop.controller.command.impl;

import com.epam.oop.bean.News;
import com.epam.oop.controller.command.Command;
import com.epam.oop.dao.util.parser.exception.ItemParsingException;
import com.epam.oop.dao.util.parser.TextParser;
import com.epam.oop.controller.command.exception.CommandExecutionException;
import com.epam.oop.service.exception.ServiceException;
import com.epam.oop.service.factory.ServiceFactory;

/**
 * Implements Command interface for add command.
 *
 * @author Uladzislau Seuruk.
 */
public class AddNews extends Command {
    /**
     * Name of command.
     */
    public static final String COMMAND_NAME = "ADD_NEWS";

    /**
     * @see Command#execute(String)
     */
    @Override
    public String execute(String params) throws CommandExecutionException {
        try {
            params = params.trim();
            News news = new TextParser().parse(params);
            ServiceFactory factory = ServiceFactory.getInstance();
            factory.getCatalogService().addNews(news);
        } catch (ItemParsingException | ServiceException e) {
            throw new CommandExecutionException(e);
        }
        return "News was successfully added.";
    }
}