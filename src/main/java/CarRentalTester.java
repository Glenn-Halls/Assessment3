import java.time.LocalDate;

public class CarRentalTester {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args){

        MenuDisplay menu = new MenuDisplay();
        CarBooking carBooking;
        Customer customer;
        CarAndBookingDates carAndBookingDates = new CarAndBookingDates();


        LocalDate today = LocalDate.now();
        LocalDate start;
        LocalDate end;

        int carsAvailable = menu.displayCarList();
        boolean makeBooking = (menu.getSelection() == 1);


        System.out.println();
        while (makeBooking) {
            int carSelected = carAndBookingDates.carSelection(carsAvailable);
            boolean validDateRange;
            boolean validStartDate;
            do {
                System.out.println("\nEnter your pickup date below:");
                do {
                    start = carAndBookingDates.getCarBookingDateFull();
                    validStartDate = (start.isAfter(today));
                    if (!validStartDate) {
                        System.out.printf("%n%sError: pickup date is in the " +
                                        "past.%s%nPlease try again.%n%n",
                                ANSI_RED, ANSI_RESET);
                    }
                } while (!validStartDate);
                System.out.println("\nEnter your drop-off date below:");
                end = carAndBookingDates.getCarBookingDateFull();
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

            carBooking = new CarBooking(start, end, carSelected);
            carBooking.makeBooking();

            System.out.println();
            customer = new Customer();

            System.out.println();
            new PrintBookingDetails(carBooking, customer);

            System.out.print("\n\n\nWould you like to make another booking?\n");

            makeBooking = (menu.getSelection() == 1);
        }
    }
}
