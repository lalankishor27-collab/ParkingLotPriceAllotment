package parking;

import java.sql.*;
import java.time.LocalDateTime;

public class TicketDAO {
    public void save(Ticket t) {
        String sql = "INSERT OR REPLACE INTO ticket(ticket_id, vehicle_id, slot_id, entry_time, exit_time, price) VALUES(?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getTicketId());
            ps.setString(2, t.getVehicleId());
            ps.setString(3, t.getSlotId());
            ps.setString(4, t.getEntryTime().toString());
            ps.setString(5, t.getExitTime() == null ? null : t.getExitTime().toString());
            ps.setObject(6, t.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Ticket findById(String id) {
        String sql = "SELECT ticket_id, vehicle_id, slot_id, entry_time, exit_time, price FROM ticket WHERE ticket_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ticket t = new Ticket(rs.getString("vehicle_id"), rs.getString("slot_id"));
                    t.setExitTime(rs.getString("exit_time") == null ? null : LocalDateTime.parse(rs.getString("exit_time")));
                    Double price = rs.getDouble("price"); if (rs.wasNull()) price = 0.0;
                    t.setPrice(price);
                    return t;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
