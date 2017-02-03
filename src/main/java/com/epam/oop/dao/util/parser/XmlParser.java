package com.epam.oop.dao.util.parser;

import com.epam.oop.bean.Category;
import com.epam.oop.bean.News;
import com.epam.oop.dao.util.converter.TextConverter;
import com.epam.oop.dao.util.converter.exception.ConversionException;
import com.epam.oop.dao.util.parser.exception.ItemParsingException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.Date;

/**
 * Parses xml files for news data.
 *
 * @author Uladzislau Seuruk.
 */
public class XmlParser {
    public XmlParser() {}

    /**
     * Parses received <tt>Node</tt> for news.
     *
     * @return <tt>News</tt> with corresponded parameters.
     * @throws ItemParsingException if some troubles were occurred while parsing.
     */
    public News parse(Node tag) throws ItemParsingException {
        if (tag == null) {
            throw new ItemParsingException("Received node was not initialized.");
        }
        NamedNodeMap map = tag.getAttributes();
        String category = getNodeValue(map, Tag.CATEGORY.toString());
        String title = getNodeValue(map, Tag.TITLE.toString());
        String date = getNodeValue(map, Tag.DATE.toString());
        try {
            TextConverter converter = new TextConverter();
            Category newsCategory = converter.convertToCategory(category);
            Date newsDate = converter.convertToDate(date);
            return new News(newsCategory, title, newsDate);
        } catch (ConversionException ce) {
            throw new ItemParsingException(ce);
        }
    }

    private String getNodeValue(NamedNodeMap map, String parameter)
            throws ItemParsingException {
        Node item = map.getNamedItem(parameter);
        if (item == null) {
            throw new ItemParsingException("Received tag is missing \""
                    + parameter + "\" parameter.");
        }
        return item.getNodeValue();
    }
}