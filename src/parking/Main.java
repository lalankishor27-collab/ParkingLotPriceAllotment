package parking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException ignored) {}

        ParkingLot lot = ParkingLot.getInstance();

        if (lot.getSlots().isEmpty()) {
            for (int i = 1; i <= 5; i++) lot.addSlot(new ParkingSlot("M" + i, VehicleType.MOTORBIKE));
            for (int i = 1; i <= 10; i++) lot.addSlot(new ParkingSlot("C" + i, VehicleType.CAR));
            for (int i = 1; i <= 3; i++) lot.addSlot(new ParkingSlot("B" + i, VehicleType.BUS));
        }

        PricingStrategy strategy = new FlatPlusHourlyPricing(15, 40.0);
        lot.setPricingStrategy(strategy);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1) Park vehicle  2) Checkout by ticket  3) Show slots  4) Active tickets  0) Exit");
            int choice = Integer.parseInt(sc.nextLine().trim());
            if (choice == 0) break;
            switch (choice) {
                case 1: {
                    System.out.print("Vehicle ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Vehicle Type (MOTORBIKE/CAR/BUS): ");
                    VehicleType vt = VehicleType.valueOf(sc.nextLine().trim().toUpperCase());
                    Ticket ticket = lot.parkVehicle(new Vehicle(id, vt));
                    if (ticket != null)
                        System.out.println("Ticket issued: " + ticket.getTicketId() + " entry=" + ticket.getEntryTime());
                    break;
                }
                case 2: {
                    System.out.print("Ticket ID: ");
                    String t = sc.nextLine().trim();
                    try {
                        double fee = lot.checkout(t);
                        System.out.println("Fee to pay: " + fee);
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                }
                case 3: {
                    for (ParkingSlot s : lot.getSlots()) System.out.println(s);
                    break;
                }
                case 4: {
                    for (Ticket tk : lot.getActiveTickets()) System.out.println(tk);
                    break;
                }
            }
        }
        sc.close();
        System.out.println("Exiting.");
    }
}
