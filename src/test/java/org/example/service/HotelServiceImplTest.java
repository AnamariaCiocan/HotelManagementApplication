package org.example.service;

import org.example.model.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {
    private Hotel hotel;
    @BeforeEach
    void setUp() {
        hotel = new Hotel(1, "HotelCreative", "Andorra", null, null, null);
    }

    @Test
    void testAddHotelDetails() {

        assertEquals("HotelCreative", hotel.getName());
        assertEquals("Andorra", hotel.getLocation());

        hotel.setName("HotelC");
        hotel.setLocation("Madrid");

        assertEquals("HotelC", hotel.getName());
        assertEquals("Madrid", hotel.getLocation());
    }

}