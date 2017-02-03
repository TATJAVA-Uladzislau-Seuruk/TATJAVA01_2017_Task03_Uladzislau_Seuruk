package com.epam.oop.dao.util.parser;

import com.epam.oop.bean.Category;
import com.epam.oop.bean.News;
import com.epam.oop.dao.util.converter.TextConverter;
import com.epam.oop.dao.util.converter.exception.ConversionException;
import com.epam.oop.dao.util.parser.exception.ItemParsingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses text files for news data.
 *
 * @author Uladzislau Seuruk.
 */
public class TextParser {
    /**
     * Symbol that separates different parameters.
     */
    private static final char PARAMETER_DELIMITER = ' ';
    /**
     * Symbol that separates parameter name from value.
     */
    private static final char VALUE_DELIMITER = '=';

    public TextParser() {}

    /**
     * Parses received <tt>String</tt> for news.
     *
     * @return <tt>News</tt> with corresponded parameters.
     * @throws ItemParsingException if some troubles were occurred while parsing.
     */
    public News parse(String params) throws ItemParsingException {
        if (params == null) {
            throw new ItemParsingException("String with parameters was not initialized.");
        }
        Map<String, String> paramsMap = getParametersMap(params);
        String categoryParam = paramsMap.get(Tag.CATEGORY.toString());
        if (categoryParam == null) {
            throw new ItemParsingException("Category is missing.");
        }
        try {
            Category category = new TextConverter().convertToCategory(categoryParam);
            String title = paramsMap.get(Tag.TITLE.toString());
            if (title == null) {
                throw new ItemParsingException("Title is missing.");
            }
            return new News(category, title, new Date());
        } catch (ConversionException ce) {
            throw new ItemParsingException(ce);
        }
    }

    private Map<String, String> getParametersMap(String params) {
        Map<String, String> paramsMap = new HashMap<>();
        int valueDelimPosition = params.indexOf(VALUE_DELIMITER);
        while (valueDelimPosition != -1) {
            String command = params.substring(0, valueDelimPosition);
            params = params.substring(valueDelimPosition + 1);
            if (params.startsWith("\"")) {
                params = params.substring(1);
                int quotePosition = params.indexOf("\"");
                String value = params.substring(0, quotePosition);
                params = params.substring(quotePosition + 1);
                paramsMap.put(command.toUpperCase(), value);
            } else {
                int paramsDelimPosition = params.indexOf(PARAMETER_DELIMITER);
                String value = params.substring(0, paramsDelimPosition);
                params = params.substring(paramsDelimPosition + 1);
                paramsMap.put(command.toUpperCase(), value);
            }
            params = params.trim();
            valueDelimPosition = params.indexOf(VALUE_DELIMITER);
        }
        return paramsMap;
    }
}