package com.epam.oop.dao.util.parser;

import com.epam.oop.bean.Category;
import com.epam.oop.bean.News;
import com.epam.oop.bean.Tag;
import com.epam.oop.util.converter.TextConverter;
import com.epam.oop.util.converter.exception.ConversionException;
import com.epam.oop.dao.util.parser.exception.ItemParsingException;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses text files for news data.
 *
 * @author Uladzislau Seuruk.
 */
public class TxtNewsParser {
    /**
     * Symbol that indicates the start of news parameter.
     */
    public static final char PARAMETER_START = '[';
    /**
     * Symbol that indicates the start of news parameter.
     */
    public static final char PARAMETER_END = ']';
    /**
     * Symbol that separates parameter name from value.
     */
    public static final char VALUE_DELIMITER = '|';

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
        Map<String, String> paramsMap = makeParametersMap(params);
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
            String date = paramsMap.get(Tag.DATE.toString());
            if (date == null) {
                throw new ItemParsingException("Date is missing.");
            }
            return new News(category, title, date);
        } catch (ConversionException ce) {
            throw new ItemParsingException(ce);
        }
    }

    private Map<String, String> makeParametersMap(String line) throws ItemParsingException  {
        Map<String, String> paramsMap = new HashMap<>();
        while (!line.equals("")) {
            int index = line.indexOf(PARAMETER_END);
            String param = line.substring(0, index + 1);
            String[] values = parseParameter(param);
            paramsMap.put(values[0].toUpperCase(), values[1]);
            line = line.substring(index + 1);
        }
        return paramsMap;
    }

    private String[] parseParameter(String line) throws ItemParsingException {
        if (!line.startsWith(String.valueOf(PARAMETER_START))) {
            throw new ItemParsingException("Data corruption");
        }
        if (!line.endsWith(String.valueOf(PARAMETER_END))) {
            throw new ItemParsingException("Data corruption");
        }
        String[] values = line.split("\\" + String.valueOf(VALUE_DELIMITER));
        values[0] = values[0].substring(1);
        values[1] = values[1].substring(0, values[1].length() - 1);
        return values;
    }
}