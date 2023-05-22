/**
 * Represents a "Premium" car, which is a subset of the Car class.
 */
public class PremiumCar extends Car {

    // Constant represents the percentage rate of insurance.
    private final double INSURANCERATE = 0.05;

    // Class constructor
    PremiumCar(String carName1, double carRate1) {
        super(carName1, carRate1);
    }

    /**
     * Overrides the car class method to get the rate of hire for a premium car
     * while factoring in the addition of the insurance premium.
     * @return Daily cost to hire.
     */
    public double getCarRate() {
        return (1 + INSURANCERATE) * carRate;
    }
}
