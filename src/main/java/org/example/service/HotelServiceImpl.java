package org.example.service;

import org.example.model.Guest;
import org.example.model.Hotel;
import org.example.model.Reservation;
import org.example.model.Room;
import org.example.repository.HotelRepo;

import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    private HotelRepo hotelRepo;

    public HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public void addHotel(Hotel hotel) {
        try {
            hotelRepo.addHotel(hotel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Hotel getHotel(int hotel_id) {
        try {
            Hotel h = hotelRepo.getHotel(hotel_id);
            if (h != null) {
                h.setRooms(h.getRooms().stream().filter(Room::getAvailable).collect(Collectors.toList()));
                h.getRooms().forEach(room -> System.out.println("room_number: " + room.getRoom_number() +
                        ", type: " + room.getType() + ", available: " + room.getAvailable()));
                return h;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
