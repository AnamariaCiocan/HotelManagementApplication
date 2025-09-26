package org.example;

import org.example.model.*;
import org.example.repository.*;
import org.example.service.*;
import org.example.util.validateGuestDetails;

import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import java.util.List;

public class Main {

    public void menu() {
        System.out.println("Options:");
        System.out.println("1. Add a new hotel");
        System.out.println("2. Add a new guest");
        System.out.println("3. Add a new room");
        System.out.println("4. Add a new reservation");
        System.out.println("5. Get details about a hotel");
        System.out.println("6. Get rooms for a hotel");
        System.out.println("7. Get room details");
        System.out.println("8. Cancel a room reservation");
        System.out.println("9. Get all reservations for a hotel");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");

    }


    public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RoomRepo roomRepo = new RoomRepositoryImpl();
        GuestRepo guestRepo = new GuestRepositoryImpl();
        HotelRepo hotelRepo = new HotelRepositoryImpl();
        ReservationRepo reservationRepo = new ReservationRepositoryImpl();

        RoomService roomService = new RoomServiceImpl(roomRepo);
        validateGuestDetails validator = new validateGuestDetails();
        GuestService guestService = new GuestServiceImpl(guestRepo, validator);
        HotelService hotelService = new HotelServiceImpl(hotelRepo);
        ReservationService reservationService = new ReservationServiceImpl(reservationRepo);

        Boolean isActive = true;
        int option;
        while (isActive) {
            menu();
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
                continue;
            }
            switch (option) {
                case 1:
                    try {
                        System.out.println("Hotel name: ");
                        String name = scanner.nextLine();
                        System.out.println("Hotel location: ");
                        String location = scanner.nextLine();
                        List<Room> rooms = new ArrayList<Room>();
                        List<Reservation> reservations = new ArrayList<>();
                        List<Guest> guests = new ArrayList<>();
                        Hotel hotel = new Hotel(name, location, rooms, guests, reservations);
                        hotelService.addHotel(hotel);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    try {
                        System.out.println("Guest name: ");
                        String guest_name = scanner.nextLine();
                        System.out.println("Guest email: ");
                        String email = scanner.nextLine();
                        System.out.println("Guest phone: ");
                        String guest_phone = scanner.nextLine();
                        System.out.println("Guest's hotel id: ");
                        int guest_hotel_id;
                        if (scanner.hasNextInt()) {
                            guest_hotel_id = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid hotel id");
                            scanner.nextLine();
                            break;
                        }
                        Guest guest = new Guest(1, guest_name, email, guest_phone, guest_hotel_id);
                        guestService.addGuest(guest);
                        System.out.println("Guest added successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                case 3:
                    try {
                        System.out.println("Room number: ");
                        int room_number;
                        if (scanner.hasNextInt()) {
                            room_number = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid room number!");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Room type (single/double): ");
                        String room_type = scanner.nextLine().trim().toUpperCase();
                        type_room roomTypeEnum = type_room.valueOf(room_type);
                        System.out.println("Room availability(true/false): ");
                        boolean room_availability;
                        String a = scanner.nextLine().toLowerCase();
                        if (a.equals("true")) room_availability = true;
                        else if (a.equals("false")) room_availability = false;
                        else {
                            System.out.println("Invalid availability");
                            break;
                        }
                        System.out.println("Room's hotel id: ");
                        int rooms_hotel_id;
                        if (scanner.hasNextInt()) {
                            rooms_hotel_id = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid hotel id");
                            scanner.nextLine();
                            break;
                        }
                        Room room = new Room(room_number, roomTypeEnum, room_availability, rooms_hotel_id);
                        roomService.addRoom(room);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    try {
                        System.out.println("Guest's id: ");
                        if (!scanner.hasNextInt()) {
                            String id = scanner.nextLine();
                            System.out.println("Invalid guest id");
                            break;
                        }
                        int guest_id = scanner.nextInt();
                        System.out.println("Hotel's id: ");
                        if (!scanner.hasNextInt()) {
                            String wrong = scanner.nextLine();
                            System.out.println("Invalid hotel id");
                            break;
                        }
                        int hotel_id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Room's id: ");
                        if (!scanner.hasNextInt()) {
                            String id = scanner.nextLine();
                            System.out.println("Invalid room id");
                            break;
                        }
                        int rooms_id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Check-in date (yyyy-mm-dd): ");
                        Date checkinDate;
                        try {
                            checkinDate = java.sql.Date.valueOf(LocalDate.parse(scanner.nextLine()));
                        } catch (Exception e) {
                            System.out.println("Invalid check-in date format");
                            break;
                        }
                        Date checkoutDate;
                        System.out.print("Check-out date (yyyy-mm-dd): ");
                        try {
                            checkoutDate = java.sql.Date.valueOf(LocalDate.parse(scanner.nextLine()));
                        } catch (Exception e) {
                            System.out.println("Invalid check-out date format");
                            break;
                        }
                        Date reservationDate;
                        System.out.print("Check-out date (yyyy-mm-dd): ");
                        try {
                            reservationDate = java.sql.Date.valueOf(LocalDate.parse(scanner.nextLine()));
                        } catch (Exception e) {
                            System.out.println("Invalid check-out date format");
                            break;
                        }
                        System.out.println("Status: ");
                        String status = scanner.nextLine();


                        Reservation reservation = new Reservation(guest_id, hotel_id, rooms_id, checkinDate, checkoutDate, reservationDate, status);
                        String resStatus = reservationService.makeReservationsForRoom(reservation);
                        System.out.println("Reservation status: " + resStatus);
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Invalid date format! Please use yyyy-MM-dd format.");
                    } catch (Exception e) {
                        System.out.println("Error creating reservation: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Hotel id: ");
                    if (!scanner.hasNextInt()) {
                        String wrong = scanner.nextLine();
                        System.out.println("Invalid hotel id: '" + wrong + "'. Returning to menu.");
                        break;
                    }
                    int hId = scanner.nextInt();
                    scanner.nextLine();
                    Hotel h = hotelService.getHotel(hId);
                    if (h == null) {
                        System.out.println("Hotel not found");
                    } else {
                        System.out.println(h.toString());
                        List<Guest> guest_lst = h.getGuests();
                        List<Reservation> reservation_lst = h.getReservations();
                        for (Guest g : guest_lst) {
                            System.out.println(g.toString());
                        }
                        for (Reservation r : reservation_lst) {
                            System.out.println(r.toString());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Hotel's id: ");
                    if (!scanner.hasNextInt()) {
                        String wrong = scanner.nextLine();
                        System.out.println("Invalid hotel id");
                        break;
                    }
                    int hotelRoomsId = scanner.nextInt();
                    scanner.nextLine();
                    List<Room> lstr = roomService.getRoomsForHotel(hotelRoomsId);
                    if (lstr == null || lstr.isEmpty()) {
                        System.out.println("No rooms for the hotel");
                    } else {
                        for (Room r : lstr) {
                            System.out.println(r.toString());
                        }
                    }
                    break;
                case 7:
                    System.out.println("Room's id: ");
                    if (!scanner.hasNextInt()) {
                        String id = scanner.nextLine();
                        System.out.println("Invalid room id");
                        break;
                    }
                    int rId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(roomService.getRoom(rId));
                    break;
                case 8:
                    System.out.println("Reservation's id: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid reservation id");
                        scanner.nextLine();
                        break;
                    }
                    int reservation_id = scanner.nextInt();
                    scanner.nextLine();
                    reservationService.cancelRoomReservation(reservation_id);
                    break;
                case 9:
                    System.out.println("Hotel's id: ");
                    if (!scanner.hasNextInt()) {
                        String id = scanner.nextLine();
                        System.out.println("Invalid hotel id");
                        break;
                    }
                    int hotell_id = scanner.nextInt();
                    scanner.nextLine();
                    List<Reservation> reservationlst = reservationService.getReservationsForHotel(hotell_id);
                    if (reservationlst == null || reservationlst.isEmpty()) {
                        System.out.println("No reservations for the hotel");
                    } else {
                        for (Reservation r : reservationlst) {
                            System.out.println(r.toString());
                        }
                    }
                    break;
                case 0:
                    isActive = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}