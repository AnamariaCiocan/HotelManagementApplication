package org.example.model;

import java.util.Date;

public class Reservation {
    private int reservation_id;
    private int guest_id;
    private int hotel_id;
    private int room_id;
    private Date checkInDate;
    private Date checkOutDate;
    private Date reservationDate;
    private String status;


    public Reservation(int reservation_id, int guest_id, int hotel_id, int room_id, Date checkInDate, Date checkOutDate, Date reservationDate, String status) {
        this.reservation_id = reservation_id;
        this.guest_id = guest_id;
        this.room_id = room_id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.status = status;
        this.hotel_id = hotel_id;
    }

    public Reservation(int guest_id, int hotel_id, int room_id, Date checkInDate, Date checkOutDate, Date reservationDate, String status) {
        this.guest_id = guest_id;
        this.room_id = room_id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.status = status;
        this.hotel_id = hotel_id;
    }

    public Reservation() {
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", guest_id=" + guest_id +
                ", room_id=" + room_id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                ", hotel_id=" + hotel_id +
                '}';
    }
}
