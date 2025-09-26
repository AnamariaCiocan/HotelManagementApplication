package org.example.service;

import org.example.model.Reservation;

import java.util.List;

public interface ReservationService {
    public String makeReservationsForRoom(Reservation reservation);

    public void cancelRoomReservation(int reservationId);

    public List<Reservation> getReservationsForHotel(int hotelId);
}
