package parking;

public class FlatPlusHourlyPricing implements PricingStrategy {
    private final long freeMinutes;
    private final double ratePerHour;

    public FlatPlusHourlyPricing(long freeMinutes, double ratePerHour) {
        this.freeMinutes = freeMinutes;
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculateFee(Ticket ticket, Vehicle vehicle) {
        long minutes = ticket.getParkedMinutes();
        if (minutes <= freeMinutes) return 0.0;
        long billableMinutes = minutes - freeMinutes;
        long hours = billableMinutes / 60;
        if (billableMinutes % 60 != 0) hours += 1;
        return hours * ratePerHour;
    }
}
