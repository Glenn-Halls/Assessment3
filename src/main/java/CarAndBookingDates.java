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


    public CarAndBookingDates(int year1, int month1, int day1) {
        year = year1;
        month = month1;
        day = day1;
    }

    public CarAndBookingDates() {
        this(0,0,0);
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
}
