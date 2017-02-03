package com.epam.oop.dao;

import com.epam.oop.bean.News;
import com.epam.oop.dao.exception.DaoException;
import com.epam.oop.dao.util.reader.NewsReader;
import com.epam.oop.dao.util.reader.exception.ReadingException;
import com.epam.oop.dao.util.reader.impl.TextReader;
import com.epam.oop.dao.util.writer.NewsWriter;
import com.epam.oop.dao.util.writer.exception.WritingException;
import com.epam.oop.dao.util.writer.impl.TxtWriter;

import java.util.*;

/**
 * Implementation of DAO layer.
 *
 * @author Uladzislau Seuruk.
 */
public class NewsDaoImpl implements NewsDao {
    /**
     * TODO:
     */
    private NewsWriter writer = new TxtWriter();
    private NewsReader reader = new TextReader();

    public NewsDaoImpl() {}

    /**
     * @see NewsDao#addNews(News)
     */
    @Override
    public void addNews(News news) throws DaoException {
        try {
            writer.writeToEnd(news);
        } catch (WritingException we) {
            throw new DaoException(we);
        }
    }

    /**
     * @see NewsDao#getAllNews()
     */
    @Override
    public List<News> getAllNews() throws DaoException {
        try {
            return new ArrayList<>(reader.read());
        } catch (ReadingException re) {
            throw new DaoException(re);
        }
    }

    /**
     * @see NewsDao#getNews(String...)
     */
    @Override
    public List<News> getNews(String... tags) throws DaoException {
        try {
            Set<News> newsSet = reader.read(tags);
            return new LinkedList<>(newsSet);
        } catch (ReadingException re) {
            throw new DaoException(re);
        }
    }


    /*
     * @see NewsDao#removeNews(News)
    @Override
    public void removeNews(News news) throws DaoException {
        return archive.remove(news);
    } */
}