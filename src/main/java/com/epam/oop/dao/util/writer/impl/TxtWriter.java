package com.epam.oop.dao.util.writer.impl;

import com.epam.oop.bean.News;
import com.epam.oop.dao.util.parser.TxtNewsParser;
import com.epam.oop.dao.util.writer.NewsWriter;
import com.epam.oop.dao.util.writer.exception.WritingException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * TODO: add comments.
 *
 * @author Uladzislau Seuruk.
 */
public class TxtWriter implements NewsWriter {
    /**
     * File to write data into.
     */
    private File file;

    /**
     * Throws <tt>WritingException</tt> if received <tt>File</tt> was not initialized.
     */
    public TxtWriter(File file) throws WritingException {
        if (file == null) {
            throw new WritingException("File was not initialized.");
        }
        this.file = file;
    }

    /**
     * Throws <tt>WritingException</tt> if received <tt>String</tt> with path to file was not
     * initialized.
     */
    public TxtWriter(String filePath) throws WritingException {
        if (filePath == null) {
            throw new WritingException("String with path to file was not initialized.");
        }
        this.file = new File(filePath);
    }

    /***
     * @see NewsWriter#writeToEnd(News)
     */
    @Override
    public void writeToEnd(News news) throws WritingException {
        if (!file.exists()) {
            throw new WritingException("File was not found.");
        }
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file, true)))) {
            out.append(makeFormattedString(news));
        } catch (IOException e) {
            throw new WritingException(e);
        }
    }

    private StringBuilder addValueToString(StringBuilder builder,
                                           String key,
                                           String value) {
        builder.append(key)
                .append(TxtNewsParser.VALUE_DELIMITER)
                .append("\"")
                .append(value)
                .append("\"")
                .append(TxtNewsParser.PARAMETER_DELIMITER);
        return builder;
    }

    private String makeFormattedString(News news) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder = addValueToString(builder, "category", news.getCategory().toString());
        builder = addValueToString(builder, "title", news.getTitle());
        builder = addValueToString(builder, "date", news.getPublicationDate());
        return  builder.toString();
    }
}