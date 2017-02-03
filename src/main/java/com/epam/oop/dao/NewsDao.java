package com.epam.oop.dao;

import com.epam.oop.bean.News;
import com.epam.oop.dao.exception.DaoException;

import java.util.List;

/**
 * TODO: add comments.
 *
 * @author Uladzislau Seuruk.
 */
public interface NewsDao {
    /**
     * .
     *
     * @param news
     * @return
     * @throws DaoException
     */
    void addNews(News news) throws DaoException;

    /**
     * Returns <tt>List</tt> with <tt>News</tt> that matches received tags.
     *
     * @param tags keywords for searching.
     * @return <tt>List</tt> with <tt>News</tt> that matches received tags.
     * @throws DaoException
     */
    List<News> getNews(String... tags) throws DaoException;

    /*
     * .
     *
     * @param news
     * @return
     * @throws DaoException
    void removeNews(News news) throws DaoException;
     */
}