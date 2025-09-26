package org.example.repository;

import org.example.model.Room;
import org.example.model.type_room;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepo {
    public int getMaxId() {
        String sql = "SELECT MAX(room_id) FROM Room";
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
    public void addRoom(Room room) {
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Room(room_id,room_number,type_room,available,hotel_id) VALUES (?,?,?,?,?)")) {
            preStmt.setInt(1, getMaxId() + 1);
            preStmt.setInt(2, room.getRoom_number());
            preStmt.setObject(3, room.getType().name().toLowerCase(), java.sql.Types.OTHER);
            preStmt.setBoolean(4, room.getAvailable());
            preStmt.setInt(5, room.getHotel_id());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error DB " + e.getMessage());
        }
    }

    @Override
    public List<Room> getRoomsForHotel(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT room_id, room_number, type_room, available, hotel_id FROM Room WHERE hotel_id=?")) {
            preStmt.setInt(1, hotelId);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                type_room roomType = type_room.valueOf(rs.getString("type_room").toUpperCase());
                Room room = new Room(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        roomType,
                        rs.getBoolean("available"),
                        rs.getInt("hotel_id")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public String getRoom(int roomId) {
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT room_id, room_number, type_room, available, hotel_id FROM Room WHERE room_id=?")) {
            preStmt.setInt(1, roomId);
            ResultSet rs = preStmt.executeQuery();

            if (rs.next()) {
                type_room roomType = type_room.valueOf(rs.getString("type_room").toUpperCase());
                Room room = new Room(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        roomType,
                        rs.getBoolean("available"),
                        rs.getInt("hotel_id")
                );
                return room.toString();
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e.getMessage());
        }
        return "no room with this id";
    }
}
