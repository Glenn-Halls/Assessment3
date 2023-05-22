
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class CarAndBookingDates {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Instance variables.
    private static int year;
    private static int month;
    private static int day;

    // Helper object
    private static final Scanner keyboard = new Scanner(System.in);

    public CarAndBookingDates() {
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        // Value of month increased by 1 as Calendar indexes months from 0.
        month = today.get(Calendar.MONTH ) + 1;
        day = today.get(Calendar.DAY_OF_MONTH);
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
                System.out.printf("%sInvalid car selection: '%d' is not on " +
                        "the list.%s%n%n", ANSI_RED, number, ANSI_RESET);
            } else {
                selection = number;
            }
            validInput = (number > 0 && number <= carsAvailable);
        } while (!validInput);
        return selection;
    }

    public static LocalDate getCarBookingDateFull() {
        LocalDate date = LocalDate.now();
        boolean validDate;
        do {
            year = promptForYear();
            month = promptForMonth();
            day = promptForDay();
            try {
                date = LocalDate.of(year, month, day);
                validDate = true;
            } catch (DateTimeException dte) {
                System.out.printf("%n%sError: date is not valid.%s%nPlease " +
                        "try again%n%n", ANSI_RED, ANSI_RESET);
                validDate = false;
            }
        } while (!validDate);
        return date;
    }

    private static int promptForYear() {
        String input;
        boolean validInput;
        int year = 0;
        System.out.print("Please enter the year - for example '2023': ");
        do {
            input = keyboard.nextLine();
            if (validateYearString(input)) {
                year = Integer.parseInt(input);
                validInput = true;
            } else {
                System.out.printf("%sInvalid year entry, try again: %s",
                        ANSI_RED, ANSI_RESET);
                validInput = false;
            }
        } while (!validInput);
        return year;
    }

    private static int promptForMonth() {
        String input;
        boolean validInput;
        int month = 0;
        System.out.print("Please enter the month number - for example '9': ");
        do {
            input = keyboard.nextLine();
            if (validateMonthString(input)) {
                month = Integer.parseInt(input);
                validInput = true;
            } else {
                System.out.printf("%sInvalid month entry, try again: %s",
                        ANSI_RED, ANSI_RESET);
                validInput = false;
            }
        } while (!validInput);
        return month;
    }

    private static int promptForDay() {
        String input;
        boolean validInput;
        int day = 0;
        System.out.print("Please enter the day - for example '12': ");
        do {
            input = keyboard.nextLine();
            if (validateDayString(input)) {
                day = Integer.parseInt(input);
                validInput = true;
            } else {
                System.out.printf("%sInvalid day entry, try again: %s",
                        ANSI_RED, ANSI_RESET);
                validInput = false;
            }
        } while (!validInput);
        return day;
    }

    private static boolean validateYearString(String yearString) {
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

    private static boolean validateMonthString(String monthString) {
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

    private static boolean validateDayString(String dayString) {
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
