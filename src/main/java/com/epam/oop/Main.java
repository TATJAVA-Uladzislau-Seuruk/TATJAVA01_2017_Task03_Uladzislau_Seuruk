package com.epam.oop;

import com.epam.oop.controller.Controller;
import com.epam.oop.controller.exception.ControllerException;

/**
 * @author Uladzislau Seuruk.
 */
public class Main {
    public static void main(String[] args) {
        /*try {
            Controller controller = new Controller();
            System.out.println(controller.executeCommand());
        } catch (ControllerException cee) {
            System.out.println(cee.getMessage());
        }*/
        try {
            Controller controller = new Controller();
            System.out.println(controller.executeCommand("add_News category=Book wololo=asd Title=\"Passed out\""));
            System.out.println(controller.executeCommand("find_News "));
        } catch (ControllerException re) {
            System.out.println(re.getMessage());
        }
    }
}