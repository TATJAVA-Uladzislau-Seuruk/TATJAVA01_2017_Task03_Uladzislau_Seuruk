package com.epam.oop.service.util;

import com.epam.oop.bean.Category;
import com.epam.oop.bean.News;
import com.epam.oop.bean.Tag;
import com.epam.oop.service.util.exception.NewsParamsParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses news parameters.
 *
 * @author Uladzislau Seuruk.
 */
public class NewsParamsParser {
    private static final Logger LOG = LogManager.getLogger("com.epam.oop.service.util.NewsParamsParser");
    /**
     * Symbol that separates different parameters.
     */
    public static final char PARAMETER_DELIMITER = ' ';
    /**
     * Symbol that separates parameter name from value.
     */
    public static final char VALUE_DELIMITER = '=';
    /**
     * Symbol that marks parameter value that contains whitespaces.
     */
    public static final char VALUE_MARKER = '\"';

    /**
     * Parses received <tt>String</tt> for news.
     *
     * @return <tt>News</tt> with corresponded parameters.
     * @throws NewsParamsParsingException if some troubles were occurred while parsing.
     */
    public static News parse(String params) throws NewsParamsParsingException {
        Map<String, String> paramsMap = makeParamsMap(params);
        String categoryParam = paramsMap.get(Tag.CATEGORY.toString());
        if (categoryParam == null) {
            throw new NewsParamsParsingException("Category is missing.");
        }

        Category category = parseCategory(categoryParam);
        String title = paramsMap.get(Tag.TITLE.toString());
        if (title == null) {
            throw new NewsParamsParsingException("Title is missing.");
        }
        if (title.isEmpty()) {
            throw new NewsParamsParsingException("Title is empty.");
        }

        String currentDate = formatDate(new Date());
        if (LOG.isDebugEnabled()) {
            LOG.debug(params + " : " + category + ", " + title + ", " + currentDate);
        }
        return new News(category, title, currentDate);
    }

    private static Map<String, String> makeParamsMap(String params) {
        Map<String, String> paramsMap = new HashMap<>();
        int valueDelimPosition = params.indexOf(VALUE_DELIMITER);
        while (valueDelimPosition != -1) {
            String command = params.substring(0, valueDelimPosition);
            params = params.substring(valueDelimPosition + 1);
            if (params.startsWith(String.valueOf(VALUE_MARKER))) {
                params = params.substring(1);
                int markerPosition = params.indexOf(VALUE_MARKER);
                String value = params.substring(0, markerPosition);
                params = params.substring(markerPosition + 1);
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

    private static String formatDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        StringBuilder builder = new StringBuilder();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        builder.append(formatSmallValue(day))
                .append(".")
                .append(formatSmallValue(month))
                .append(".")
                .append(calendar.get(Calendar.YEAR));
        if (LOG.isDebugEnabled()) {
            LOG.debug(date.toString() + " : " + builder.toString());
        }
        return builder.toString();
    }

    private static String formatSmallValue(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }

    private static Category parseCategory(String request) throws NewsParamsParsingException {
        if (request == null) {
            throw new NewsParamsParsingException("String with category was not initialized.");
        }
        Category category;
        try {
            category = Category.valueOf(request.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NewsParamsParsingException("Unknown category.");
        }
        return category;
    }

    private NewsParamsParser() {}
}