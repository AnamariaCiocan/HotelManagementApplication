package org.example.model;

public class Room {
    private int id;
    private int room_number;
    private type_room type;
    private Boolean available;
    private int hotel_id;

    public Room(int id, int room_number, type_room type, Boolean available, int hotel_id) {
        this.id = id;
        this.room_number = room_number;
        this.type = type;
        this.available = available;
        this.hotel_id = hotel_id;
    }

    public Room(int room_number, type_room type, Boolean available, int hotel_id) {
        this.room_number = room_number;
        this.type = type;
        this.available = available;
        this.hotel_id = hotel_id;
    }

    public Room() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public type_room getType() {
        return type;
    }

    public void setType(type_room type) {
        this.type = type;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", room_number=" + room_number +
                ", type='" + type + '\'' +
                ", available=" + available +
                ", hotel_id=" + hotel_id +
                '}';
    }
}
