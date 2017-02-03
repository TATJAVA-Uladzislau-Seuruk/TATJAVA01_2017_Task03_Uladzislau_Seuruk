package com.epam.oop.view;

import com.epam.oop.controller.Controller;
import com.epam.oop.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of View layer.
 *
 * @author Uladzislau Seuruk.
 */
public class View {
    private static final Logger LOG = LogManager.getRootLogger();
    /**
     * Singleton of this class.
     */
    private static View instance = new View();

    /**
     * Returns instance of this class.
     */
    public static View getInstance() {
        return instance;
    }

    /**
     * Makes request to application.
     *
     * @param request <tt>String</tt> with request.
     */
    public void makeRequest(String request) {
        try {
            Controller controller = new Controller();
            System.out.println(controller.executeCommand(request) + "\n");
        } catch (ControllerException re) {
            LOG.error("Request execution error", re);
        }
    }
}