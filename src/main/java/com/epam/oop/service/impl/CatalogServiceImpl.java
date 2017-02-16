package com.epam.oop.service.impl;

import com.epam.oop.bean.News;
import com.epam.oop.dao.exception.DaoException;
import com.epam.oop.dao.factory.DaoFactory;
import com.epam.oop.service.CatalogService;
import com.epam.oop.service.exception.ServiceException;
import com.epam.oop.service.util.NewsParamsParser;
import com.epam.oop.service.util.exception.NewsParamsParsingException;

import java.util.List;

/**
 * Implementation of Service layer.
 *
 * @author Uladzislau Seuruk.
 */
public class CatalogServiceImpl implements CatalogService {
    /**
     * @see CatalogService#addNews(String)
     */
    @Override
    public void addNews(String params) throws ServiceException {
        if (params == null) {
            throw new ServiceException("String with news parameters was not initialized.");
        }
        try {
            params = params.trim();
            News news = NewsParamsParser.parse(params);
            DaoFactory factory = DaoFactory.getInstance();
            factory.getNewsDao().addNews(news);
        } catch (DaoException | NewsParamsParsingException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @see CatalogService#getNews(String)
     */
    @Override
    public List<News> getNews(String tags) throws ServiceException {
        if (tags == null) {
            throw new ServiceException("Sting with tags was not initialized.");
        }
        try {
            tags = tags.trim();
            String[] params = tags.split(String.valueOf(NewsParamsParser.PARAMETER_DELIMITER));
            DaoFactory factory = DaoFactory.getInstance();
            return factory.getNewsDao().getNews(params);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}