public class PremiumCar extends Car {

    private final double INSURANCERATE = 0.05;

    PremiumCar(String carName1, double carRate1) {
        super(carName1, carRate1);
    }

    public double getCarRate() {
        return (1 + INSURANCERATE) * carRate;
    }
}
