package org.example.service;

import org.example.model.Room;

import java.util.List;

public interface RoomService {
    public void addRoom(Room room);

    public List<Room> getRoomsForHotel(int hotelId);

    public String getRoom(int roomId);
}
