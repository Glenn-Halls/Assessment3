import java.time.LocalDate;

public class Test {
    public static void test() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2023, 9, 20);
        Customer dummyCustomer = new Customer("Some Name", "email@email.com",
                "123 Some Street, Suburb, Vic 3000");
        CarBooking dummyBooking = new CarBooking(today, tomorrow, 1);
        dummyBooking.makeBooking();
        new PrintBookingDetails(dummyBooking, dummyCustomer);
    }
}