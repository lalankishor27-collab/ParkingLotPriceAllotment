package parking;

import java.util.Set;

public class MonthlyPassPricing implements PricingStrategy {
    private final Set<String> passHolderVehicleIds;
    public MonthlyPassPricing(Set<String> passHolderVehicleIds) {
        this.passHolderVehicleIds = passHolderVehicleIds;
    }

    @Override
    public double calculateFee(Ticket ticket, Vehicle vehicle) {
        if (passHolderVehicleIds.contains(vehicle.getVehicleId())) {
            return 0.0;
        } else {
            long hours = ticket.getParkedHoursRoundedUp();
            return hours * 30.0;
        }
    }
}
