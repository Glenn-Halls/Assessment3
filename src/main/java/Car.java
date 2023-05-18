public class Car {

    private double carRate;
    private String carName;

    // Class constructor
    Car(String carName1, double carRate1) {
        this.carName = carName1;
        this.carRate = carRate1;
    }

    // Accessor methods
    public double getCarRate() { return carRate; }
    public String getCarName() { return carName; }

}
