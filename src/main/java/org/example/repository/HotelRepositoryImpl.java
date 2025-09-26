package org.example.repository;

import org.example.model.*;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryImpl implements HotelRepo {
    public int getMaxId() {
        String sql = "SELECT MAX(hotel_id) FROM Hotel";
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
    public void addHotel(Hotel hotel) {
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("INSERT INTO Hotel(hotel_id,name,location) VALUES (?,?,?)")) {
            preStmt.setInt(1, getMaxId());
            preStmt.setString(2, hotel.getName());
            preStmt.setString(3, hotel.getLocation());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB Error " + e.getMessage());
        }

    }

    @Override
    public Hotel getHotel(int hotel_id) {
        Hotel hotel = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sqlHotel = "SELECT hotel_id, name, location FROM Hotel WHERE hotel_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlHotel)) {
                stmt.setInt(1, hotel_id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    hotel = new Hotel(
                            rs.getInt("hotel_id"),
                            rs.getString("name"),
                            rs.getString("location"),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );
                }
            }

            if (hotel == null) {
                return null;
            }

            String sqlRooms = "SELECT room_id, room_number, type_room, available FROM Room WHERE hotel_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlRooms)) {
                stmt.setInt(1, hotel_id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Room room = new Room(
                            rs.getInt("room_id"),
                            rs.getInt("room_number"),
                            type_room.valueOf(rs.getString("type_room").toUpperCase()),
                            rs.getBoolean("available"),
                            hotel_id
                    );
                    hotel.getRooms().add(room);
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement("SELECT reservation_id, guest_id, hotel_id, room_id, checkInDate, checkOutDate, reservationDate, status FROM Reservation WHERE hotel_id = ?")) {
                stmt.setInt(1, hotel_id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getInt("guest_id"),
                            rs.getInt("room_id"),
                            hotel_id,
                            rs.getDate("checkInDate"),
                            rs.getDate("checkOutDate"),
                            rs.getDate("reservationDate"),
                            rs.getString("status")

                    );
                    hotel.getReservations().add(reservation);
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT g.guest_id, g.name, g.email, g.phone FROM Guest g  WHERE g.hotel_id = ?")) {
                stmt.setInt(1, hotel_id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Guest guest = new Guest(
                            rs.getInt("guest_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            hotel_id
                    );
                    hotel.getGuests().add(guest);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }


}
