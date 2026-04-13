package parking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String DB_URL = "jdbc:sqlite:parking.db";

    static {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            String createVehicle = """
            CREATE TABLE IF NOT EXISTS vehicle (
                vehicle_id TEXT PRIMARY KEY,
                type TEXT NOT NULL
            );
            """;
            String createSlot = """
            CREATE TABLE IF NOT EXISTS slot (
                slot_id TEXT PRIMARY KEY,
                allowed_type TEXT NOT NULL,
                occupied INTEGER NOT NULL
            );
            """;
            String createTicket = """
            CREATE TABLE IF NOT EXISTS ticket (
                ticket_id TEXT PRIMARY KEY,
                vehicle_id TEXT NOT NULL,
                slot_id TEXT NOT NULL,
                entry_time TEXT NOT NULL,
                exit_time TEXT,
                price REAL,
                FOREIGN KEY(vehicle_id) REFERENCES vehicle(vehicle_id),
                FOREIGN KEY(slot_id) REFERENCES slot(slot_id)
            );
            """;
            st.execute(createVehicle);
            st.execute(createSlot);
            st.execute(createTicket);
        } catch (SQLException e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
