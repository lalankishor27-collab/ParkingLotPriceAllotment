package parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDAO {
    public void save(Vehicle v) {
        String sql = "INSERT OR REPLACE INTO vehicle(vehicle_id, type) VALUES(?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, v.getVehicleId());
            ps.setString(2, v.getType().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Vehicle findById(String id) {
        String sql = "SELECT vehicle_id, type FROM vehicle WHERE vehicle_id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(rs.getString("vehicle_id"), VehicleType.valueOf(rs.getString("type")));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
