package com.epam.oop.service;

import com.epam.oop.bean.News;
import com.epam.oop.service.exception.ServiceException;

import java.util.List;

/**
 * TODO: add comments.
 *
 * @author Uladzislau Seuruk.
 */
public interface CatalogService {
    /**
     * .
     *
     * @param news
     * @throws ServiceException
     */
    void addNews(News news) throws ServiceException;

    /**
     * Returns <tt>List</tt> with <tt>News</tt> that matches received tags.
     *
     * @param tags keywords for searching.
     * @return <tt>List</tt> with <tt>News</tt> that matches received tags.
     * @throws ServiceException
     */
    List<News> getNews(String... tags) throws ServiceException;

    /*
     * .
     *
     * @param news
     * @throws ServiceException
    void removeNews(News news) throws ServiceException;
     */
}