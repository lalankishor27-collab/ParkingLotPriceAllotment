package parking;

public class HourlyPricing implements PricingStrategy {
    private final double ratePerHour;

    public HourlyPricing(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculateFee(Ticket ticket, Vehicle vehicle) {
        long hours = ticket.getParkedHoursRoundedUp();
        return hours * ratePerHour;
    }
}
