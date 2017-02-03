package com.epam.oop.dao.factory;

import com.epam.oop.dao.NewsDao;
import com.epam.oop.dao.NewsDaoImpl;

/**
 * TODO: add comments.
 *
 * @author Uladzislau Seuruk.
 */
public class DaoFactory {
    /**
     * .
     */
    private static DaoFactory instance = new DaoFactory();
    /**
     * .
     */
    private NewsDao newsDaoImpl = new NewsDaoImpl();

    /**
     * .
     */
    public static DaoFactory getInstance() {
        return instance;
    }

    /**
     * .
     */
    public NewsDao getNewsDao() {
        return newsDaoImpl;
    }
}