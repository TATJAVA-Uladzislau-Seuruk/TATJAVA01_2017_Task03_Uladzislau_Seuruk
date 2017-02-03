package com.epam.oop.controller.exception;

/**
 * Exception for Controller layer.
 *
 * @author Uladzislau Seuruk.
 */
public class ControllerException extends Exception {
    public ControllerException() {}

    public ControllerException(Exception e) {
        super(e);
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }
}