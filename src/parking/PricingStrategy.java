package parking;

public interface PricingStrategy {
    double calculateFee(Ticket ticket, Vehicle vehicle);
}
