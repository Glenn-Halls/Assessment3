import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

public class PrintBookingDetails {

    // Class values
    final int SCREEN_WIDTH = 70;
    final int PAGE_WIDTH = 50;
    final int MARGIN = (SCREEN_WIDTH - PAGE_WIDTH)/2;
    final int COLUMN_WIDTH = 20;
    final double INSURANCE = 0.05;


    public PrintBookingDetails(CarBooking carBooking, Customer customer) {
        // Helper method to format number into currency.
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // Helper method to format date.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        // Helper for formatting decimals
        DecimalFormat df = new DecimalFormat("#.##");

        // Allocation of car and booking values into String variables
        String customerName = customer.getCustomerName();
        String customerEmail = customer.getCustomerEmail();
        String customerAddress = customer.getCustomerAddress();
        String carName = carBooking.getCarName();
        String startDate = carBooking.getStartDate().format(dtf);
        String endDate = carBooking.getEndDate().format(dtf);
        String numDays = String.valueOf(carBooking.getTotalDays());
        double rate = carBooking.getNewRate();
        String ratePerDay = nf.format(rate);
        boolean premiumCar = (carBooking.car instanceof PremiumCar);
        String isCarPremium = (premiumCar) ? "Yes" : "No";
        double price = (premiumCar) ? rate / (1 + INSURANCE) : rate;
        String advertisedPrice = nf.format(price);
        String insurance = ((premiumCar) ? df.format(INSURANCE*100) : 0) + "%";
        String totalCost = nf.format(carBooking.getCost());





        // Booking details banner
        printBanner(SCREEN_WIDTH, '-');
        pageBanner('*');
        centreText("Thank you for your booking!");
        pageBanner('*');
        System.out.println();
        centreText("Your booking summary is below");
        System.out.println();
        pageBanner('~');
        System.out.println();

        // Customer information
        printLine("Name:", customerName);
        printLine("Email:", customerEmail);
        printLine("Address:", customerAddress);
        pageBanner('.');
        System.out.println();

        // Booking information
        centreText("Booking information");
        System.out.println();
        printLine("Car", carName);
        printLine("First day of hire", startDate);
        printLine("Last day of hire", endDate);
        printLine("Number of days", numDays);
        printLine("Premium car?", isCarPremium);
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

    private void printLine(String leftColumn, String rightColumn) {
        printWhitespace(MARGIN);
        System.out.print(leftColumn);
        printWhitespace(PAGE_WIDTH - COLUMN_WIDTH - leftColumn.length());
        System.out.print(rightColumn);
        System.out.println();
    }

}
