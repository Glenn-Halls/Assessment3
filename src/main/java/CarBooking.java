import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


/**
 * Performs calculations of car booking duration and costs.
 */
public class CarBooking {
    // Instance variables
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int carNumber;
    private int totalDays;
    private double newRate;
    private double cost;
    private String carName;
    public Car car;

    // Constants for error message colors and file path.
    private static final String FILE_PATH = "src/res/CarList.csv";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Constructor method to initialise instance variables.
    public CarBooking(LocalDate start, LocalDate end, int carNum) {
        startDate = start;
        endDate = end;
        carNumber = carNum;
    }

    /**
     * Reads the car list CSV file. If there is an error in doing so, will
     * display an appropriate error message and quit. If successful, will
     * calculate days hired and cost of the booking using calculateTotalDays
     * and calculateCost methods respectively.
     */
    void makeBooking() {
        String[] carDetails = new String[6];
        File carList = new File(FILE_PATH);
        boolean premiumCar = false;
        double dailyRate;
        try {
            Scanner fileText = new Scanner(carList);
            for (int i = 1; i <= carNumber; i++) {
                String line = fileText.nextLine();
                if (i == carNumber) {
                    carDetails = line.split(",");
                }
            }
            premiumCar = (carDetails[4].toLowerCase().contains("premium"));
            dailyRate = Double.parseDouble(carDetails[5]);
            carName = carDetails[1];
            cost = dailyRate;
            if (premiumCar) {
                car = new PremiumCar(carName, dailyRate);
            } else {
                car = new Car(carName, dailyRate);
            }
            fileText.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%sError: file not found.%s%n",
                    ANSI_RED, ANSI_RESET);
            System.exit(1);
        } catch (SecurityException e) {
            System.out.printf("%sError: file access denied.%s%n",
                    ANSI_RED, ANSI_RESET);
            System.out.println(e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.out.printf("%sError: an unknown error occurred.%s%n",
                    ANSI_RED, ANSI_RESET);
            System.out.println(e.getMessage());
            System.exit(3);
        }
        newRate = (premiumCar) ? 1.05 * cost : cost;
        calculateTotalDays();
        calculateCost(newRate, totalDays);
    }

    /**
     * Calculates the difference in days between the start and end date of hire.
     */
    private void calculateTotalDays() {
        totalDays = (int)ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Calculates the total cost of the car hire.
     * @param newRate Represents the hire rate, after application of insurance
     *                premium.
     * @param totalDays Represents the number of days of the booking.
     */
    private void calculateCost(double newRate, long totalDays) {
        cost = totalDays * newRate;
    }

    // Accessor methods for instance variables.
    double getCost() { return cost; }
    LocalDate getStartDate() { return startDate; }
    LocalDate getEndDate() { return endDate; }
    long getTotalDays() { return totalDays; }
    int getCarNumber() { return carNumber; }
    double getNewRate() { return newRate; }
    String getCarName() { return carName; }
}
