
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Manages user selections of start and end dates of the booking, and the car
 * that the user would like to hire.
 */

public class CarAndBookingDates {

    // Constants for error message colour
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_RESET = "\u001B[0m";

    // Instance variables.
    private int year;
    private int month;
    private int day;

    // Helper object
    private final Scanner keyboard = new Scanner(System.in);

    /**
     * Constructor initialises instance variables with the default values of
     * today's date.
     */
    public CarAndBookingDates() {
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        // Value of month increased by 1 as Calendar indexes months from 0.
        month = today.get(Calendar.MONTH ) + 1;
        day = today.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Prompts user for car selection from previously displayed list
     * @param carsAvailable Represents the number of cars on the list. User must
     *                      select a number between 1 and this value.
     * @return User car selection.
     */
    public int carSelection(int carsAvailable) {
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

    /**
     * Calls the year, month and day booking date inputs to create a valid
     * LocalDate output. If user selects an invalid date, an appropriate error
     * message will be displayed and the user will be prompted to try again.
     * @return valid LocalDate.
     */
    public LocalDate getCarBookingDateFull() {
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

    /**
     * Prompts user for a valid year. If it is not a valid year, user will be
     * given a descriptive error message and prompted to try again.
     * @return Valid year.
     */
    private int promptForYear() {
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

    /**
     * Prompts the user to enter a valid month. If not an integer between 1 and
     * 12, user will be provided an error message and prompted to try again.
     * @return Valid month.
     */
    private int promptForMonth() {
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

    /**
     * Prompts the user to enter a valid month. If not an integer between 1 and
     * 31, user will be provided an error message and prompted to try again.
     * @return Valid day.
     */
    private int promptForDay() {
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

    // Validates that year is valid and not in the past.
    private boolean validateYearString(String year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int yearNum;
        if (year.length() != 4) return false;
        try {
            yearNum = Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return (yearNum >= currentYear);
    }

    // Validates that month is valid.
    private boolean validateMonthString(String month) {
        if (month.length() < 1 || month.length() > 2) {
            return false;
        }
        try {
            int monthNum = Integer.parseInt(month);
            return (monthNum >= 1 && monthNum <= 13);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    // Validates that day is valid.
    private boolean validateDayString(String day) {
        if (day.length() < 1 || day.length() > 2) {
            return false;
        }
        try {
            int dayNum = Integer.parseInt(day);
            return (dayNum >= 1 && dayNum <= 31);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
