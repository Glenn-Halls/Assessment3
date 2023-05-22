/**
 * Class holds information about the car to be hired.
 */

public class Car {

    // Represents the daily cost of hire.
    double carRate;
    // Represents the make and model of the car to be hired.
    private final String carName;

    // Class constructor
    Car(String carName1, double carRate1) {
        this.carName = carName1;
        this.carRate = carRate1;
    }

    // Accessor methods
    double getCarRate() { return carRate; }
    String getCarName() { return carName; }

}
