package org.example.repository;

import org.example.model.Reservation;

import java.util.List;

public interface ReservationRepo {
    public String makeReservationsForRoom(Reservation reservation);

    public String cancelRoomReservation(int reservationId);

    public List<Reservation> getReservationsForHotel(int hotelId);
}
