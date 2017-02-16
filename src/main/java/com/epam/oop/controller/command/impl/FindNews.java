package com.epam.oop.controller.command.impl;

import com.epam.oop.bean.News;
import com.epam.oop.controller.command.Command;
import com.epam.oop.service.exception.ServiceException;
import com.epam.oop.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implements Command interface for search command.
 *
 * @author Uladzislau Seuruk.
 */
public class FindNews implements Command {
    private static final Logger LOG = LogManager.getRootLogger();
    /**
     * Name of command.
     */
    public static final String COMMAND_NAME = "FIND_NEWS";

    /**
     * @see Command#execute(String)
     */
    @Override
    public String execute(String params) {
        String response;
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            List<News> newsList = factory.getCatalogService().getNews(params);
            StringBuilder builder = new StringBuilder();
            for (News news : newsList) {
                appendNewsInfo(builder, news);
            }
            response = "Found news:" + builder.toString();
        } catch (ServiceException se) {
            LOG.error(se.getMessage(), se);
            response = "Error while news search.";
        }
        return response;
    }

    private void appendNewsInfo(StringBuilder builder, News news) {
        builder.append("\n")
                .append("\"")
                .append(news.getTitle())
                .append("\" Category: \"")
                .append(news.getCategory())
                .append("\" Publication Date: ")
                .append(news.getPublicationDate());
    }
}