package com.epam.oop.dao.util.reader.factory;

import com.epam.oop.dao.util.reader.NewsReader;
import com.epam.oop.dao.util.reader.impl.TextReader;
import com.epam.oop.dao.util.reader.impl.XmlReader;

/**
 * TODO: add comments.
 *
 * @author Uladzislau Seuruk.
 */
public class ReaderFactory {
    /**
     * .
     */
    private static ReaderFactory instance = new ReaderFactory();
    /**
     * .
     */
    private NewsReader textReader = new TextReader();
    /**
     * .
     */
    private NewsReader xmlReader = new XmlReader();

    /**
     * .
     */
    public static ReaderFactory getInstance() {
        return instance;
    }

    /**
     * .
     */
    public NewsReader getTextReader() {
        return textReader;
    }

    /**
     * .
     */
    public NewsReader getXmlReader() {
        return xmlReader;
    }
}