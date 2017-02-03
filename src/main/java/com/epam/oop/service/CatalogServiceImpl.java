package com.epam.oop.service;

import com.epam.oop.bean.News;
import com.epam.oop.dao.exception.DaoException;
import com.epam.oop.dao.factory.DaoFactory;
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
            throw new ServiceException("News was not initialized.");
        }
        try {
            NewsParamsParser parser = new NewsParamsParser();
            News news = parser.parse(params);
            DaoFactory factory = DaoFactory.getInstance();
            factory.getNewsDao().addNews(news);
        } catch (DaoException | NewsParamsParsingException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @see CatalogService#getNews(String...)
     */
    @Override
    public List<News> getNews(String... tags) throws ServiceException {
        if (tags == null) {
            throw new ServiceException("Tags was not initialized.");
        }
        DaoFactory factory = DaoFactory.getInstance();
        try {
            return factory.getNewsDao().getNews(tags);
        } catch (DaoException de) {
            throw new ServiceException(de);
        }
    }

    /*
     * @see CatalogService#removeNews(String)
    @Override
    public void removeNews(String params) throws ServiceException {
    }
     */
}