package parking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {
    public void save(ParkingSlot s) {
        String sql = "INSERT OR REPLACE INTO slot(slot_id, allowed_type, occupied) VALUES(?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getSlotId());
            ps.setString(2, s.getAllowedType().name());
            ps.setInt(3, s.isOccupied() ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ParkingSlot findById(String id) {
        String sql = "SELECT slot_id, allowed_type, occupied FROM slot WHERE slot_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ParkingSlot s = new ParkingSlot(rs.getString("slot_id"), VehicleType.valueOf(rs.getString("allowed_type")));
                    s.setOccupied(rs.getInt("occupied") == 1);
                    return s;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public List<ParkingSlot> findAll() {
        String sql = "SELECT slot_id, allowed_type, occupied FROM slot ORDER BY slot_id";
        ArrayList<ParkingSlot> list = new ArrayList<ParkingSlot>();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ParkingSlot s = new ParkingSlot(rs.getString("slot_id"), VehicleType.valueOf(rs.getString("allowed_type")));
                s.setOccupied(rs.getInt("occupied") == 1);
                list.add(s);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
