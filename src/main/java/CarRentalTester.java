import java.time.LocalDate;

/**
 * Entry point to the program.
 * Accesses main menu, customer details and car booking information.
 * Uses a looping structure to prompt user for input of car selection, booking
 * dates and customer information. After a successful booking, prompts customer
 * if they would like to make another booking. If yes, loop will repeat. If no,
 * program will exit.
 *
 * @author Glenn Halls
 */
public class CarRentalTester {

    // Constants for error message colour.
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Entry point to the program.
    public static void main(String[] args){

        // Instance variables
        MenuDisplay menu = new MenuDisplay();
        CarBooking carBooking;
        Customer customer;
        CarAndBookingDates carAndBookingDates = new CarAndBookingDates();
        LocalDate today = LocalDate.now();
        LocalDate start;
        LocalDate end;

        // Display list of cars and prompt user to continue with booking.
        int carsAvailable = menu.displayCarList();
        boolean makeBooking = (menu.getSelection() == 1);

        /*
         * While loop prompts user for booking information and continues to loop
         * as long as user decides to continue with another booking.
         */
        System.out.println();
        while (makeBooking) {
            // Prompts user to select car via CarAndBookingDates class.
            int carSelected = carAndBookingDates.carSelection(carsAvailable);
            // Booleans for continuation or otherwise of loop
            boolean validDateRange;
            boolean validStartDate;
            do {
                System.out.println("\nEnter your pickup date below:");
                do {
                    // Prompt user for pickup date via CarAndBookingDates class.
                    start = carAndBookingDates.getCarBookingDateFull();
                    validStartDate = (start.isAfter(today) ||
                            start.equals(today));
                    if (!validStartDate) {
                        System.out.printf("%n%sError: pickup date is in the " +
                                        "past.%s%nPlease try again.%n%n",
                                ANSI_RED, ANSI_RESET);
                    }
                } while (!validStartDate);
                System.out.println("\nEnter your drop-off date below:");
                // Prompt user for drop-off date via CarAndBookingDates class.
                end = carAndBookingDates.getCarBookingDateFull();
                // Ensure that end date is after start date, retry if not.
                int dateCompare = start.compareTo(end);
                if (dateCompare == 0) {
                    System.out.printf("%n%sError: pickup and drop-off date " +
                                    "are the same.%s%nPlease try again.%n",
                            ANSI_RED, ANSI_RESET);
                    validDateRange = false;
                } else if (dateCompare > 0) {
                    System.out.printf("%n%sError: drop-off date is before" +
                                    " pickup.%s%nPlease try again.%n", ANSI_RED,
                            ANSI_RESET);
                    validDateRange = false;
                } else {
                    validDateRange = true;
                }
            } while (!validDateRange);

            // Create CarBooking now start date, end date and car are confirmed.
            carBooking = new CarBooking(start, end, carSelected);
            carBooking.makeBooking();

            // Create new Customer object. Default constructor requires input.
            System.out.println();
            customer = new Customer();

            /*
             * Now that the CarBooking (including start and end date and car
             * selection) and Customer objects have been successfully created,
             * display all relevant details of the booking and customer
             * information to the user, using PrintBookingDetails class.
             */
            System.out.println();
            new PrintBookingDetails(carBooking, customer);

            // Prompt user to either quit or make another booking.
            System.out.print("\n\n\nWould you like to make another booking?\n");
            makeBooking = (menu.getSelection() == 1);
        }
    }
}
