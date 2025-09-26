package org.example.util;

import java.util.Scanner;

public class Utils {
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            } else {
                System.out.println("Input cannot be empty.");
            }
        }
    }

    public static boolean readBoolean(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) return true;
            else if (input.equals("false")) return false;
            else System.out.println("Invalid input. Please enter true or false.");
        }
    }

    public static java.util.Date readDate(Scanner scanner, String prompt) {
        while (true) {
            try {
                String input = readString(scanner, prompt + " (yyyy-mm-dd): ");
                java.time.LocalDate localDate = java.time.LocalDate.parse(input);
                return java.sql.Date.valueOf(localDate);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
    }
}
