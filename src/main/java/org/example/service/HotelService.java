package org.example.service;

import org.example.model.Hotel;

public interface HotelService {
    public void addHotel(Hotel hotel);

    public Hotel getHotel(int hotel_id);
}
