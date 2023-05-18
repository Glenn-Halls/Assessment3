import java.time.LocalDate;

public class Test {
    public static void test() {

        int numCars = MenuDisplay.displayCarList();
        int bookOrQuit = MenuDisplay.getSelection();
        int carSelected = CarAndBookingDates.carSelection(numCars);
        LocalDate startDate = new CarAndBookingDates().getCarBookingDateFull();
        LocalDate endDate = new CarAndBookingDates().getCarBookingDateFull();
        System.out.println(bookOrQuit);
        System.out.println(carSelected);
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.print("\n\n\n");
        int dateCompare = startDate.compareTo(endDate);
        if (dateCompare > 0) {
            System.out.println("Start date > end date");
        } else if (dateCompare == 0) {
            System.out.println("Start date = end date");
        } else {
            System.out.println("start date < end date");
        }
        if (startDate.isBefore(endDate)) {
            System.out.println("Start date is before end date");
        } else if (startDate.equals(endDate)) {
            System.out.println("Start date is the same as end date");
        } else {
            System.out.println("Start date is after end date");
        }


    }
}