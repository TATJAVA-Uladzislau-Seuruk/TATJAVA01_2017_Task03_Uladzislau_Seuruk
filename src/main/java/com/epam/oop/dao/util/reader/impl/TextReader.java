package com.epam.oop.dao.util.reader.impl;

import com.epam.oop.bean.News;
import com.epam.oop.dao.util.parser.TextParser;
import com.epam.oop.dao.util.parser.exception.ItemParsingException;
import com.epam.oop.dao.util.reader.NewsReader;
import com.epam.oop.dao.util.reader.exception.ReadingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Reads news data from text file.
 *
 * @author Uladzislau Seuruk.
 */
public class TextReader implements NewsReader {
    /**
     * Default path to file.
     */
    private static final String DEFAULT_FILE_PATH = System.getProperty("user.dir")
            + File.separator + "data.txt";
    /**
     * File to parse.
     */
    private File file;

    {
        this.file = new File(DEFAULT_FILE_PATH);
    }

    public TextReader() {}

    /**
     * Initializes internal field if received parameter is not <tt>null</tt>.
     *
     * @param file <tt>File</tt> to parse.
     */
    public TextReader(File file) {
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
    public TextReader(String filePath) {
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
        try (Scanner scanner = new Scanner(file)) {
            TextParser parser = new TextParser();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                boolean match = true;
                for (String param : args) {
                    if (!line.toLowerCase().contains(param.toLowerCase())) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    newsSet.add(parser.parse(line));
                }
            }
        } catch (FileNotFoundException | ItemParsingException e) {
            throw new ReadingException(e);
        }
        return newsSet;
    }
}