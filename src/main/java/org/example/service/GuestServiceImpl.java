package org.example.service;

import org.example.model.Guest;
import org.example.repository.GuestRepo;
import org.example.repository.HotelRepo;
import org.example.util.validateGuestDetails;

import javax.xml.validation.Validator;

public class GuestServiceImpl implements GuestService {
    private GuestRepo guestRepo;
    //    private HotelRepo hotelRepo;
    private validateGuestDetails validator;

    public GuestServiceImpl() {

    }

    public GuestServiceImpl(GuestRepo guestRepository, validateGuestDetails validator) {
        this.guestRepo = guestRepository;
        this.validator = new validateGuestDetails();
    }

    @Override
    public void addGuest(Guest guest) {
        try {
            if (validator.isValid(guest.name(), guest.email(), guest.phone())) {
                guestRepo.addGuest(guest);

            } else {
                System.out.println("Invalid Guest details");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
