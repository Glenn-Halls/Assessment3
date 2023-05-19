import java.time.DateTimeException;
import java.time.LocalDate;

public class CarRentalTester {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args){

        LocalDate today = LocalDate.now();
        LocalDate start = null;
        LocalDate end;

        int carsAvailable = MenuDisplay.displayCarList();
        boolean makeBooking = (MenuDisplay.getSelection() == 1);


        System.out.println();
        while (makeBooking) {
            int carSelected = CarAndBookingDates.carSelection(carsAvailable);
            boolean validDates;
            boolean validStartDate;
            boolean validEndDate;
            do {
                System.out.println("\nEnter your pickup date below:");
                do {
                    try {
                        start = CarAndBookingDates.getCarBookingDateFull();
                    } catch (DateTimeException dte) {
                        System.out.printf("%n%sError: pickup date is not " +
                                        "valid.%s%nPlease try again.%n%n",
                                ANSI_RED, ANSI_RESET);
                        validStartDate = false;
                        continue;
                    }
                    validStartDate = (start.isAfter(today));
                    if (!validStartDate) {
                        System.out.printf("%n%sError: pickup date is in the " +
                                        "past.%s%nPlease try again.%n%n",
                                ANSI_RED, ANSI_RESET);
                    }
                } while (!validStartDate);
                System.out.println("\nEnter your drop-off date below:");
                end = CarAndBookingDates.getCarBookingDateFull();
                int dateCompare = start.compareTo(end);
                if (dateCompare == 0) {
                    System.out.printf("%n%sError: pickup and drop-off date " +
                                    "are the same.%s%nPlease try again.%n",
                            ANSI_RED, ANSI_RESET);
                    validDates = false;
                } else if (dateCompare > 0) {
                    System.out.printf("%n%sError: drop-off date is before" +
                                    " pickup.%s%nPlease try again.%n", ANSI_RED,
                            ANSI_RESET);
                    validDates = false;
                } else {
                    validDates = true;
                }
            } while (!validDates);

            CarBooking carBooking = new CarBooking(start, end, carSelected);
            carBooking.makeBooking();

            System.out.println();
            Customer customer = new Customer();

            System.out.println();
            new PrintBookingDetails(carBooking, customer);

            System.out.print("\n\n\nWould you like to make another booking?\n");

            makeBooking = (MenuDisplay.getSelection() == 1);
        }
    }
}
