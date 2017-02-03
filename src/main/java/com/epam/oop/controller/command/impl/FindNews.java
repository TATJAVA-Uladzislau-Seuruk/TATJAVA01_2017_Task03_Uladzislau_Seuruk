package com.epam.oop.controller.command.impl;

import com.epam.oop.bean.News;
import com.epam.oop.controller.command.Command;
import com.epam.oop.controller.command.exception.CommandExecutionException;
import com.epam.oop.service.exception.ServiceException;
import com.epam.oop.service.factory.ServiceFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Implements Command interface for find command.
 *
 * @author Uladzislau Seuruk.
 */
public class FindNews extends Command {
    /**
     * Name of command.
     */
    public static final String COMMAND_NAME = "FIND_NEWS";
    /**
     * Symbol that separates search words.
     */
    private static final char WORDS_DELIMITER = ' ';

    /**
     * @see Command#execute(String)
     */
    @Override
    public String execute(String params) throws CommandExecutionException {
        params = params.trim();
        String[] args = params.split(String.valueOf(WORDS_DELIMITER));
        ServiceFactory factory = ServiceFactory.getInstance();
        try {
            List<News> newsList = factory.getCatalogService().getNews(args);
            StringBuilder builder = new StringBuilder();
            for (News news : newsList) {
                builder.append(getNewsInfo(news));
            }
            return builder.toString();
        } catch (ServiceException se) {
            throw new CommandExecutionException(se);
        }
    }

    private String getNewsInfo(News news) {
        Date newsDate = news.getPublicationDate();
        StringBuilder builder = new StringBuilder();
        builder.append("\"")
                .append(news.getTitle())
                .append("\" Category: \"")
                .append(news.getCategory())
                .append("\" Publication Date: ")
                .append(getFormattedDate(newsDate))
                .append("\n");
        return  builder.toString();
    }

    private String getFormattedDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(".")
                .append(calendar.get(Calendar.MONTH) + 1)
                .append(".")
                .append(calendar.get(Calendar.YEAR));
        return builder.toString();
    }
}