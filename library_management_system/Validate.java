package library_management_system;

import library_management_system.book.Book;
import library_management_system.exceptions.NoContextInputException;

public class Validate {
    public static String validateInput(String s) {
        if (s.isEmpty()) {
            throw new NoContextInputException("Input cannot be null!");
        }

        return s.trim();
    }

    public static int validateIntegerInput(String s) {
        if (s.isEmpty()) {
            throw new NoContextInputException("Input cannot be null!");
        }

        return Integer.parseInt(s);
    }

    public static boolean validateAvailableInput(String s) {
        if (s == "" || s.equalsIgnoreCase("true")) {
            return true;
        }else if (s.equalsIgnoreCase("false")) {
            return false;
        }else {
            throw new IllegalArgumentException("Invalid input. Must be true or false.");
        }
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

    public static String validateIdInput(String s) {
        String formatStr = s.toUpperCase();
        if (!formatStr.matches(Book.getIdPattern())) {
            throw new IllegalArgumentException("Invalid input format. Id must be Bxxx(eg: B001)!");
        }

        return formatStr.trim();
    }
}
