package com.epam.oop.service;

import com.epam.oop.bean.News;
import com.epam.oop.dao.exception.DaoException;
import com.epam.oop.dao.factory.DaoFactory;
import com.epam.oop.service.exception.ServiceException;

import java.util.List;

/**
 * Implementation of Service layer.
 *
 * @author Uladzislau Seuruk.
 */
public class CatalogServiceImpl implements CatalogService {
    /**
     * @see CatalogService#addNews(News)
     */
    @Override
    public void addNews(News news) throws ServiceException {
        if (news == null) {
            throw new ServiceException("News was not initialized.");
        }
        DaoFactory factory = DaoFactory.getInstance();
        try {
            factory.getNewsDao().addNews(news);
        } catch (DaoException de) {
            throw new ServiceException(de);
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
     * @see CatalogService#removeNews(News)
    @Override
    public void removeNews(News news) throws ServiceException {
        if (news == null) {
            throw new ServiceException("News was not initialized.");
        }
        DaoFactory factory = DaoFactory.getInstance();
        try {
            factory.getNewsDao().removeNews(news);
        } catch (DaoException de) {
            throw new ServiceException(de);
        }
    }
     */
}