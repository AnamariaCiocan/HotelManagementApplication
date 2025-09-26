package org.example.service;

import org.example.model.Room;
import org.example.repository.RoomRepo;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public RoomServiceImpl() {
    }

    @Override
    public void addRoom(Room room) {
        try {
            roomRepo.addRoom(room);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Room> getRoomsForHotel(int hotelId) {
        try {
            return roomRepo.getRoomsForHotel(hotelId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getRoom(int roomId) {
        try {
            return roomRepo.getRoom(roomId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "An error occured";
    }
}
