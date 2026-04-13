package parking;

import java.time.LocalTime;

public class TimeBasedPricing implements PricingStrategy {
    private final LocalTime peakStart;
    private final LocalTime peakEnd;
    private final double peakRatePerHour;
    private final double offPeakRatePerHour;

    public TimeBasedPricing(LocalTime peakStart, LocalTime peakEnd,
                            double peakRatePerHour, double offPeakRatePerHour) {
        this.peakStart = peakStart;
        this.peakEnd = peakEnd;
        this.peakRatePerHour = peakRatePerHour;
        this.offPeakRatePerHour = offPeakRatePerHour;
    }

    @Override
    public double calculateFee(Ticket ticket, Vehicle vehicle) {
        LocalTime entry = ticket.getEntryTime().toLocalTime();
        double rate = (entry.isAfter(peakStart) && entry.isBefore(peakEnd)) ? peakRatePerHour : offPeakRatePerHour;
        long hours = ticket.getParkedHoursRoundedUp();
        return hours * rate;
    }
}
