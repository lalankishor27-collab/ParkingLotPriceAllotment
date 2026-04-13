package parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final String vehicleId;
    private final String slotId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double price;

    public Ticket(String vehicleId, String slotId) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() { return ticketId; }
    public String getVehicleId() { return vehicleId; }
    public String getSlotId() { return slotId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public long getParkedMinutes() {
        LocalDateTime end = exitTime == null ? LocalDateTime.now() : exitTime;
        Duration d = Duration.between(entryTime, end);
        return d.toMinutes();
    }

    public long getParkedHoursRoundedUp() {
        long minutes = getParkedMinutes();
        long hours = minutes / 60;
        if (minutes % 60 != 0) hours += 1;
        return hours;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId='" + ticketId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", slotId='" + slotId + '\'' +
                ", entry=" + entryTime +
                ", exit=" + exitTime +
                ", price=" + price +
                "}";
    }
}
