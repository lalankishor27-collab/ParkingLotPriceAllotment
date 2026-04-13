package parking;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;
    private final Map<String, ParkingSlot> slots = new LinkedHashMap<String, ParkingSlot>();
    private final Map<String, Ticket> activeTickets = new HashMap<String, Ticket>();
    private final Map<String, Vehicle> vehicles = new HashMap<String, Vehicle>();
    private PricingStrategy pricingStrategy;

    private final SlotDAO slotDAO = new SlotDAO();
    private final VehicleDAO vehicleDAO = new VehicleDAO();
    private final TicketDAO ticketDAO = new TicketDAO();

    private ParkingLot() {
        List<ParkingSlot> list = slotDAO.findAll();
        for (ParkingSlot s : list) slots.put(s.getSlotId(), s);
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addSlot(ParkingSlot slot) {
        slots.put(slot.getSlotId(), slot);
        slotDAO.save(slot);
    }

    private ParkingSlot findAvailableSlot(VehicleType type) {
        for (ParkingSlot s : slots.values()) {
            if (!s.isOccupied() && s.getAllowedType() == type) return s;
        }
        return null;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSlot slot = findAvailableSlot(vehicle.getType());
        if (slot == null) {
            System.out.println("No available slot for " + vehicle.getType());
            return null;
        }
        slot.setOccupied(true);
        vehicles.put(vehicle.getVehicleId(), vehicle);
        vehicleDAO.save(vehicle);
        slotDAO.save(slot);
        Ticket ticket = new Ticket(vehicle.getVehicleId(), slot.getSlotId());
        activeTickets.put(ticket.getTicketId(), ticket);
        ticketDAO.save(ticket);
        System.out.println("Parked vehicle " + vehicle.getVehicleId() + " at slot " + slot.getSlotId());
        return ticket;
    }

    public double checkout(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket id");
        }
        ticket.setExitTime(LocalDateTime.now());
        Vehicle v = vehicles.get(ticket.getVehicleId());
        double fee = pricingStrategy.calculateFee(ticket, v);
        ticket.setPrice(fee);
        ParkingSlot slot = slots.get(ticket.getSlotId());
        if (slot != null) {
            slot.setOccupied(false);
            slotDAO.save(slot);
        }
        ticketDAO.save(ticket);
        activeTickets.remove(ticketId);
        vehicles.remove(ticket.getVehicleId());
        System.out.println("Checked out ticket " + ticketId + " price=" + fee);
        return fee;
    }

    public Collection<ParkingSlot> getSlots() { return slots.values(); }
    public Collection<Ticket> getActiveTickets() { return activeTickets.values(); }
}
