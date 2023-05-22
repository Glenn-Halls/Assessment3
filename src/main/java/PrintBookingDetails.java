import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

/**
 * Displays details of the finalised booking to the user.
 */
public class PrintBookingDetails {

    // Class values
    final int SCREEN_WIDTH = 70;
    final int PAGE_WIDTH = 50;
    final int MARGIN = (SCREEN_WIDTH - PAGE_WIDTH)/2;
    final int COLUMN_WIDTH = 20;
    final double INSURANCE = 0.05;

    /**
     * Class constructor will receive the car booking and customer object, then
     * will present all relevant details of the booking to the user, in a
     * formatted and tabulated display.
     * @param carBooking Represents the booking.
     * @param customer Represents the customer.
     */
    public PrintBookingDetails(CarBooking carBooking, Customer customer) {
        // Helper method to format number into currency display.
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // Helper method to format date.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        // Helper for formatting decimals.
        DecimalFormat df = new DecimalFormat("#.##");

        // Daily cost of car hire.
        double rate = carBooking.getNewRate();

        // Indicates weather or not car is premium.
        boolean premiumCar = (carBooking.car instanceof PremiumCar);

        // Represents the advertised cost of car hire for a premium car.
        double price = (premiumCar) ? rate / (1 + INSURANCE) : rate;

        // Allocation of car and booking values into String variables
        String customerName = customer.getCustomerName();
        String customerEmail = customer.getCustomerEmail();
        String customerAddress = customer.getCustomerAddress();
        String carName = carBooking.getCarName();
        String startDate = carBooking.getStartDate().format(dtf);
        String endDate = carBooking.getEndDate().format(dtf);
        String numDays = String.valueOf(carBooking.getTotalDays());
        String ratePerDay = nf.format(rate);
        String isCarPremium = (premiumCar) ? "Yes" : "No";
        String advertisedPrice = nf.format(price);
        String insurance = ((premiumCar) ? df.format(INSURANCE*100) : 0) + "%";
        String totalCost = nf.format(carBooking.getCost());

        // Booking details banner.
        printBanner(SCREEN_WIDTH, '-');
        pageBanner('*');
        centreText("Thank you for your booking!");
        pageBanner('*');
        System.out.println();
        centreText("Your booking summary is below");
        System.out.println();
        pageBanner('~');
        System.out.println();

        // Customer information.
        printLine("Name:", customerName);
        printLine("Email:", customerEmail);
        printLine("Address:", customerAddress);
        pageBanner('.');
        System.out.println();

        // Booking information.
        centreText("Booking information");
        System.out.println();
        printLine("Car", carName);
        printLine("First day of hire", startDate);
        printLine("Last day of hire", endDate);
        printLine("Number of days", numDays);
        printLine("Premium car?", isCarPremium);
        // Additional lines will be displayed only if selected car is "Premium".
        if (premiumCar) {
            printLine("Advertised price", advertisedPrice);
            printLine("Insurance rate", insurance);
        }
        printLine("Rate per day", ratePerDay);
        pageBanner('-');
        centreText("Total cost: " + totalCost);
        pageBanner('-');
        printBanner(SCREEN_WIDTH, '_');
    }

    // Helper method to print banner decoration.
    private void printBanner(int width, char decoration) {
        for (int i = 0; i < width; i++) {
            System.out.print(decoration);
        }
        System.out.println();
    }

    // Helper method to print whitespace for margin & column space.
    private void printWhitespace(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print(" ");
        }
    }

    // Helper method to print banner decoration for page width.
    private void pageBanner(char decoration) {
        printWhitespace(MARGIN);
        printBanner(PAGE_WIDTH, decoration);
    }

    // Helper method to centre text in middle of display area.
    private void centreText(String message) {
        if (message.length() >= PAGE_WIDTH) {
            System.out.println();
        } else {
            int margin = (PAGE_WIDTH - message.length())/2;
            printWhitespace(margin);
            printWhitespace(MARGIN);
            System.out.println(message);
        }
    }

    /*
     * Helper method will print string variables in the left and right columns
     * of the display as dictated by the margin, page width and column widths.
     */
    private void printLine(String leftColumn, String rightColumn) {
        printWhitespace(MARGIN);
        System.out.print(leftColumn);
        printWhitespace(PAGE_WIDTH - COLUMN_WIDTH - leftColumn.length());
        System.out.print(rightColumn);
        System.out.println();
    }
}
