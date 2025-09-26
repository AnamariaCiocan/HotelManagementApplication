package org.example.repository;

import org.example.model.Reservation;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepo {
    public int getMaxId() {
        String sql = "SELECT MAX(reservation_id) FROM Reservation";
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
    public String makeReservationsForRoom(Reservation reservation) {
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Reservation(reservation_id,guest_id,hotel_id,room_id,checkInDate,checkOutDate,reservationDate,status) VALUES (?,?,?,?,?,?,?,?) ")) {
            preStmt.setInt(1, getMaxId() + 1);
            preStmt.setInt(2, reservation.getGuest_id());
            preStmt.setInt(3, reservation.getHotel_id());

            preStmt.setInt(4, reservation.getRoom_id());
            preStmt.setDate(5, (Date) reservation.getCheckInDate());
            preStmt.setDate(6, (Date) reservation.getCheckOutDate());
            preStmt.setDate(7, (Date) reservation.getReservationDate());
            preStmt.setString(8, "confirmed");
            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DB Error " + ex.getMessage());
        }

        String updateSql = "UPDATE Room SET available = false WHERE room_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(updateSql)) {
            stmt.setInt(1, reservation.getRoom_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DB Error " + ex.getMessage());
        }
        return reservation.getStatus();
    }

    @Override
    public String cancelRoomReservation(int reservationId) {
        Connection con = DatabaseConnection.getConnection();
        int roomId = -1;
        try (PreparedStatement preStmt = con.prepareStatement("SELECT room_id FROM Reservation WHERE reservation_id = ?")) {
            preStmt.setInt(1, reservationId);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                roomId = rs.getInt("room_id");
            } else {
                return "No reservation with this id";
            }
        } catch (SQLException ex) {
            System.out.println("DB Error " + ex.getMessage());
        }
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Reservation SET status = 'canceled' WHERE reservation_id = ?")) {
            preStmt.setInt(1, reservationId);
            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DB Error " + ex.getMessage());
        }
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Room SET available = true WHERE room_id = ?")) {
            preStmt.setInt(1, roomId);
            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DB Error " + ex.getMessage());
        }
        return "canceled";
    }

    @Override
    public List<Reservation> getReservationsForHotel(int hotelId) {
        List<Reservation> reservations = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT reservation_id, guest_id, hotel_id, room_id, checkInDate, checkOutDate, reservationDate, status FROM Reservation WHERE hotel_id = ?")) {
            preStmt.setInt(1, hotelId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("guest_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("room_id"),
                        rs.getDate("checkInDate"),
                        rs.getDate("checkOutDate"),
                        rs.getDate("reservationDate"),
                        rs.getString("status")


                );
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            System.out.println("DB Error " + ex.getMessage());
        }
        return reservations;
    }
}
