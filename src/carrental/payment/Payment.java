package carrental.payment;
import carrental.reservations.Reservation;
public class Payment {
    private Integer paymentId;         // Wrapper class for flexibility
    private Reservation reservation;   // Associated reservation
    private Double amount;             // Wrapper class to allow null if needed
    private String paymentMethod;
    private String date;

    public Payment(Integer paymentId, Reservation reservation, Double amount, String paymentMethod, String date) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public void process() {
        System.out.println("Processed payment of $" + amount + " via " + paymentMethod + " on " + date);
    }

    public void refund() {
        System.out.println("Refunded payment of $" + amount + " for reservation ID: " + reservation.getReservationId());
    }

    // Getters
    public Integer getPaymentId() {
        return paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDate() {
        return date;
    }
}