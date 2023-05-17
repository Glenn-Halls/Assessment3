import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        CarAndBookingDates test = new CarAndBookingDates();
        LocalDate date = test.getCarBookingDateFull();
        LocalDate secondToday = LocalDate.now();
        System.out.println(today);
        System.out.println(date);
        System.out.println(secondToday);
        System.out.println(today.compareTo(secondToday));
        System.out.println(date.compareTo(today));
    }
}