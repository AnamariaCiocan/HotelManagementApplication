package org.example.repository;

import org.example.model.Guest;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class GuestRepositoryImpl implements GuestRepo {


    public int getMaxId() {
        String sql = "SELECT MAX(guest_id) FROM Guest";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void addGuest(Guest guest) {
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("INSERT INTO Guest(guest_id,name,email,phone,hotel_id) VALUES (?,?,?,?,?)")) {
            preStmt.setInt(1, getMaxId() + 1);
            preStmt.setString(2, guest.name());
            preStmt.setString(3, guest.email());
            preStmt.setString(4, guest.phone());
            preStmt.setInt(5, guest.hotel_id());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB Error " + e.getMessage());
        }

    }
}
