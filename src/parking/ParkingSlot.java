package parking;

public class ParkingSlot {
    private final String slotId;
    private final VehicleType allowedType;
    private boolean occupied;

    public ParkingSlot(String slotId, VehicleType allowedType) {
        this.slotId = slotId;
        this.allowedType = allowedType;
        this.occupied = false;
    }

    public String getSlotId() { return slotId; }
    public VehicleType getAllowedType() { return allowedType; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    @Override
    public String toString() {
        return "Slot{" + slotId + ", type=" + allowedType + ", occ=" + occupied + "}";
    }
}
