import java.time.LocalDate;

public class CarRentalTester {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2023, 5, 19);
        CarBooking test = new CarBooking(today, tomorrow, 4);
        test.makeBooking();
        System.out.printf("""
                
                Cost = %f
                Start Date = %s
                End Date = %s
                Total Days = %d
                Car Number = %d
                New Rate = %f
                Car Name = %s
                
                """,
                test.getCost(),
                test.getStartDate(),
                test.getEndDate(),
                test.getTotalDays(),
                test.getCarNumber(),
                test.getNewRate(),
                test.getCarName());

        Car car = new Car(test.getCarName(), test.getCost());
        System.out.println(car.getCarName());
        System.out.println(car.getCarRate());
        System.out.println(car);
    }

}
