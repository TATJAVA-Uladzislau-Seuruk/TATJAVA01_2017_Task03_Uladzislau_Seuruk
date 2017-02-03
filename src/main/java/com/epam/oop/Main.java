package com.epam.oop;

import com.epam.oop.view.View;

/**
 * @author Uladzislau Seuruk.
 */
public class Main {
    public static void main(String[] args) {
        View view = View.getInstance();
        view.makeRequest("add_News Title=\"Passed out\"");
        view.makeRequest("add_News category=film Title=\"Passed out\"");
        view.makeRequest("find_News");
        view.makeRequest("find_News film");
        view.makeRequest("find_News 100");
    }
}