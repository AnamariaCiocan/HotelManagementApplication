package org.example.model;

import java.util.List;

public class Hotel {
    private int hotel_id;
    private String name;
    private String location;
    private List<Room> rooms;
    private List<Guest> guests;
    private List<Reservation> reservations;

    public Hotel(int hotel_id, String name, String location, List<Room> rooms, List<Guest> guests, List<Reservation> reservations) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.guests = guests;
        this.reservations = reservations;
    }

    public Hotel(String name, String location, List<Room> rooms, List<Guest> guests, List<Reservation> reservations) {
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.guests = guests;
        this.reservations = reservations;
    }

    public Hotel() {
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rooms=" + rooms +
                ", guests=" + guests +
                ", reservations=" + reservations +
                '}';
    }
}
