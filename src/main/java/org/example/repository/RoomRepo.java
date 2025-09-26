package org.example.repository;

import org.example.model.Room;

import java.util.List;

public interface RoomRepo {
    public void addRoom(Room room);

    public List<Room> getRoomsForHotel(int hotelId);

    public String getRoom(int roomId);
}
