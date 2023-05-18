import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class CarBooking {
    // Instance variables
    private LocalDate startDate;
    private LocalDate endDate;
    private int carNumber;
    private int totalDays;
    private double newRate;
    private double cost;
    private String carName;


    private static final String FILE_PATH = "src/res/CarList.csv";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public CarBooking(LocalDate start, LocalDate end, int carNum) {
        startDate = start;
        endDate = end;
        carNumber = carNum;
    }

    public void makeBooking() {
        String[] carDetails = new String[6];
        File carList = new File(FILE_PATH);
        boolean premiumCar = false;
        try {
            Scanner fileText = new Scanner(carList);
            for (int i = 1; i <= carNumber; i++) {
                String line = fileText.nextLine();
                if (i == carNumber) {
                    carDetails = line.split(",");
                }
            }
            carName = carDetails[1];
            premiumCar = (carDetails[4].toLowerCase().contains("premium"));
            cost = Double.parseDouble(carDetails[5]);
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
        newRate = (premiumCar) ? 1.05 : 1;
        calculateTotalDays();
        calculateCost(newRate, totalDays);
    }

    private void calculateTotalDays() {
        totalDays = endDate.compareTo(startDate);
    }

    private void calculateCost(double newRate, long totalDays) {
        cost = totalDays * cost * newRate;
    }

    public double getCost() { return cost; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public long getTotalDays() { return totalDays; }
    public int getCarNumber() { return carNumber; }
    public double getNewRate() { return newRate; }
    public String getCarName() { return carName; }
}
