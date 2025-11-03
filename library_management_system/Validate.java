package library_management_system;

import library_management_system.exceptions.NoContextInputException;

public class Validate {
    public static String validateInput(String s) {
        if (s.isEmpty()) {
            throw new NoContextInputException("Input cannot be null!");
        }

        return s.trim();
    }

    public static boolean validateYesNo(String s) {
        if (s.isEmpty()) {
            throw new NoContextInputException("Input cannot be null");
        }

        if (s.equalsIgnoreCase("y")) {
            return true;
        }else if (s.equalsIgnoreCase("n")) {
            return false;
        }else {
            throw new IllegalArgumentException("Invalid input. Only y or n!");
        }
    }
}
