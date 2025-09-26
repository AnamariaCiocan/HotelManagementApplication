package org.example.repository;

import org.example.model.Hotel;

public interface HotelRepo {
    public void addHotel(Hotel hotel);

    public Hotel getHotel(int hotel_id);
}
