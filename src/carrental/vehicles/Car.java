package carrental.vehicles;

public class Car {
    private int carId;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private double rentalPricePerDay;
    private CarStatus status;

    public Car(int carId, String make, String model, int year, String licensePlate, double rentalPricePerDay) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.status = CarStatus.AVAILABLE; // default status
    }

    public void updateStatus(CarStatus newStatus) {
        this.status = newStatus;
    }

    public double calculateRentalCost(int days) {
        return rentalPricePerDay * days;
    }

    // Getters
    public int getCarId() { return carId; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getLicensePlate() { return licensePlate; }
    public double getRentalPricePerDay() { return rentalPricePerDay; }
    public CarStatus getStatus() { return status; }
}