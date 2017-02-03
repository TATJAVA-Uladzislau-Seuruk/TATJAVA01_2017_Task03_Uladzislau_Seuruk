package com.epam.oop.view;

import com.epam.oop.controller.Controller;
import com.epam.oop.controller.exception.ControllerException;

/**
 * Implementation of View layer.
 *
 * @author Uladzislau Seuruk.
 */
public class View {
    private static View instance = new View();

    private View() {}

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
            System.out.println(controller.executeCommand(request));
        } catch (ControllerException re) {
            System.out.println(re.getMessage());
        }
    }
}