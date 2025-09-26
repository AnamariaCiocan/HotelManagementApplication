package org.example.model;

public record Guest(
        int guest_id,
        String name,
        String email,
        String phone,
        int hotel_id
) {
}
