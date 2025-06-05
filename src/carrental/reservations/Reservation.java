package carrental.reservations;

import carrental.users.*;
import carrental.vehicles.Car;
import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isConfirmed;

    public Reservation(int reservationId, Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isConfirmed = false;
    }

    public void confirm() {
        isConfirmed = true;
        car.updateStatus(carrental.vehicles.CarStatus.RENTED);
    }

    public void cancel() {
        isConfirmed = false;
        car.updateStatus(carrental.vehicles.CarStatus.AVAILABLE);
    }

    public boolean isActive(LocalDate today) {
        return isConfirmed && (today.isEqual(startDate) || (today.isAfter(startDate) && today.isBefore(endDate)));
    }

    // Getters
    public int getReservationId() { return reservationId; }
    public Customer getCustomer() { return customer; }
    public Car getCar() { return car; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isConfirmed() { return isConfirmed; }
}