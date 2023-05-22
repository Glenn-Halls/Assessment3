public class Car {

    double carRate;
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
