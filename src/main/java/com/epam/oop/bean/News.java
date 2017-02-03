package com.epam.oop.bean;

import java.util.Date;

/**
 * Provides information about single news.
 *
 * @author Uladzislau Seuruk.
 */
public class News {
    /**
     * Category of this news.
     */
    private Category category = null;
    /**
     * Publication date of this news.
     */
    private Date publicationDate = null;
    /**
     * Title of this news.
     */
    private String title = null;

    public News() {
    }

    public News(Category category, String title, Date date) {
        this.category = category;
        this.title = title;
        this.publicationDate = date;
    }

    /**
     * Getter.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Getter.
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Getter.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Setter.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    //TODO: add equals, hashCode and serializable methods.
}