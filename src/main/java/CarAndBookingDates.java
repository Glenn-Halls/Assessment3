
import java.util.Calendar;
import java.util.Scanner;

public class CarAndBookingDates {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Instance variables.
    private int year;
    private int month;
    private int day;

    // Helper object
    static Scanner keyboard = new Scanner(System.in);


    public CarAndBookingDates() {
        year = 0;
        month = 0;
        day = 0;
    }

    public static int carSelection(int carsAvailable) {
        int selection = 0;
        boolean validInput;
        do {
            System.out.print("Select the desired car number from the list: ");
            int number;
            String input = keyboard.nextLine();
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                System.out.printf("%sInvalid car selection: enter a valid " +
                        "number.%s%n%n", ANSI_RED, ANSI_RESET);
                validInput = false;
                continue;
            }
            if (number < 1 || number > carsAvailable) {
                System.out.printf("%sInvalid car selection: \"%d\" is not on " +
                        "the list.%s%n%n", ANSI_RED, number, ANSI_RESET);
            } else {
                selection = number;
            }
            validInput = (number > 0 && number <= carsAvailable);
        } while (!validInput);
        return selection;
    }

    public static int getInt(int minChars, int maxChars, String prompt,
                             String dataLabel) {
        int number = 0;
        boolean validInput;
        String input;
        do {
            System.out.print(prompt + ": ");
            input = keyboard.nextLine();
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException nfe){
                validInput = false;
                System.out.printf("%sInvalid %s selection: not a valid " +
                        "number.%s%n%n", ANSI_RED, dataLabel, ANSI_RESET);
                continue;
            }
            if (input.length() < minChars) {
                System.out.printf("%sInvalid %s selection: not enough digits" +
                                ".%s%n%n", ANSI_RED, dataLabel, ANSI_RESET);
            } else if (input.length() > maxChars) {
                System.out.printf("%sInvalid %s selection: too many digits.%s" +
                        "%n%n", ANSI_RED, dataLabel, ANSI_RESET);
            }
            validInput = (input.length() >= minChars &&
                    input.length() <= maxChars);
        } while (!validInput);
        return number;
    }

    public static int promptForYear() {
        return getInt(4, 4, "Please enter the year - for example '2023'",
                "year");
    }

    public static int promptForMonth() {
        return getInt(1, 2, "Please enter the month number - for example '7'",
                "month");
    }

    public static int promptForDay() {
        return getInt(1, 2, "Please enter the day number - for example '21'",
                "day");
    }

    public static boolean validateYearString(String yearString) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int year;
        if (yearString.length() != 4) return false;
        try {
            year = Integer.parseInt(yearString);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return (year >= currentYear);
    }

    public static boolean validateMonthString(String monthString) {
        if (monthString.length() < 1 || monthString.length() > 2) {
            return false;
        }
        try {
            int month = Integer.parseInt(monthString);
            return (month >= 1 && month <= 13);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean validateDayString(String dayString) {
        if (dayString.length() < 1 || dayString.length() > 2) {
            return false;
        }
        try {
            int day = Integer.parseInt(dayString);
            return (day >= 1 && day <= 31);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }



}
