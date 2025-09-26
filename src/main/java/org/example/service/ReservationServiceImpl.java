package org.example.service;

import org.example.model.Reservation;
import org.example.repository.ReservationRepo;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private ReservationRepo reservationRepo;

    public ReservationServiceImpl(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public ReservationServiceImpl() {
    }

    @Override
    public String makeReservationsForRoom(Reservation reservation) {
        try {
            String status = "";
            status = reservationRepo.makeReservationsForRoom(reservation);
            return status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "An error occured";
    }

    @Override
    public void cancelRoomReservation(int reservationId) {
        try {
            reservationRepo.cancelRoomReservation(reservationId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Reservation> getReservationsForHotel(int hotelId) {
        try {
            List<Reservation> res = reservationRepo.getReservationsForHotel(hotelId);
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
