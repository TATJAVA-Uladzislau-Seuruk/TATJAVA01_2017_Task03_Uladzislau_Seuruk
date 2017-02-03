package com.epam.oop.service.factory;

import com.epam.oop.service.CatalogService;
import com.epam.oop.service.CatalogServiceImpl;

/**
 * TODO: add comments.
 *
 * @author Uladzislau Seuruk.
 */
public class ServiceFactory {
    /**
     * .
     */
    private static ServiceFactory instance = new ServiceFactory();
    /**
     * .
     */
    private CatalogService catalogServiceImpl = new CatalogServiceImpl();

    /**
     * .
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * .
     */
    public CatalogService getCatalogService() {
        return catalogServiceImpl;
    }
}