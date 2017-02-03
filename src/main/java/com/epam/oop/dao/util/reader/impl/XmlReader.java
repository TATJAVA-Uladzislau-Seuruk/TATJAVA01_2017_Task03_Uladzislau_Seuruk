package com.epam.oop.dao.util.reader.impl;

import com.epam.oop.bean.News;
import com.epam.oop.dao.util.parser.exception.ItemParsingException;
import com.epam.oop.dao.util.parser.XmlParser;
import com.epam.oop.dao.util.reader.NewsReader;
import com.epam.oop.dao.util.reader.exception.ReadingException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Reads news data from xml file.
 *
 * @author Uladzislau Seuruk.
 */
public class XmlReader implements NewsReader {
    /**
     * Default path to file.
     */
    private static final String DEFAULT_FILE_PATH = System.getProperty("user.dir")
            + File.separator + "data.xml";
    /**
     * Default primary tag name.
     */
    private static final String PRIMARY_TAG = "item";
    /**
     * File to parse.
     */
    private File file;

    {
        this.file = new File(DEFAULT_FILE_PATH);
    }

    public XmlReader() {}

    /**
     * Initializes internal field if received parameter is not <tt>null</tt>.
     *
     * @param file <tt>File</tt> to parse.
     */
    public XmlReader(File file) {
        if (file != null) {
            this.file = file;
        }
    }

    /**
     * If received parameter is not <tt>null</tt> initializes internal field by file with received
     * path.
     *
     * @param filePath <tt>String</tt> with path to file to parse.
     */
    public XmlReader(String filePath) {
        if (filePath != null) {
            this.file = new File(filePath);
        }
    }

    /**
     * @see NewsReader#read(String... args)
     */
    @Override
    public Set<News> read(String... args) throws ReadingException {
        if (!file.exists()) {
            throw new ReadingException("File was not found.");
        }
        Set<News> newsSet = new HashSet<>();
        XmlParser parser = new XmlParser();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList tagList = document.getElementsByTagName(PRIMARY_TAG);
            for (int i = 0; i < tagList.getLength(); ++i) {
                News news = parser.parse(tagList.item(i));
                newsSet.add(news);
            }
        } catch (IOException | ParserConfigurationException | SAXException | ItemParsingException e) {
            throw new ReadingException(e);
        }
        return newsSet;
    }
}