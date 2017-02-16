package com.epam.oop.dao.factory;

import com.epam.oop.dao.NewsDao;
import com.epam.oop.dao.impl.NewsDaoImpl;

/**
 * Implements Factory design pattern.
 *
 * @author Uladzislau Seuruk.
 */
public class DaoFactory {
    /**
     * Singleton of this class.
     */
    private static DaoFactory instance = new DaoFactory();
    /**
     * Instance of DAO layer.
     */
    private NewsDao newsDaoImpl = new NewsDaoImpl();

    /**
     * Returns instance of this class.
     */
    public static DaoFactory getInstance() {
        return instance;
    }

    /**
     * Returns instance of DAO layer.
     */
    public NewsDao getNewsDao() {
        return newsDaoImpl;
    }
}