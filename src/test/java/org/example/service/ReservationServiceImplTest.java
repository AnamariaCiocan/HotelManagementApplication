package org.example.service;

import org.example.model.Reservation;
import org.example.repository.ReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepo reservationRepo;

    @InjectMocks
    private ReservationServiceImpl reservationService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void makeReservationsForRoom() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationRepo.makeReservationsForRoom(reservation)).thenReturn("Reservation confirmed");

        // Act
        String result = reservationService.makeReservationsForRoom(reservation);

        // Assert
        assertEquals("Reservation confirmed", result);
        verify(reservationRepo, times(1)).makeReservationsForRoom(reservation);
    }

    @Test
    void makeReservationsForRoom_exception() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationRepo.makeReservationsForRoom(reservation))
                .thenThrow(new RuntimeException("DB error"));

        // Act
        String result = reservationService.makeReservationsForRoom(reservation);

        // Assert
        assertEquals("An error occured", result);
        verify(reservationRepo, times(1)).makeReservationsForRoom(reservation);
    }


    @Test
    void cancelRoomReservation() {
        // Arrange
        int reservationId = 123;

        // Act
        reservationService.cancelRoomReservation(reservationId);

        // Assert
        verify(reservationRepo, times(1)).cancelRoomReservation(reservationId);
    }

    @Test
    void cancelRoomReservation_exception() {
        // Arrange
        int reservationId = 123;
        doThrow(new RuntimeException("DB error")).when(reservationRepo).cancelRoomReservation(reservationId);

        // Act
        reservationService.cancelRoomReservation(reservationId);

        // Assert
        verify(reservationRepo, times(1)).cancelRoomReservation(reservationId);

    }
}