package edu.library.service;

import edu.library.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Long userId, Long bookId);
    void cancelReservation(Long reservationId);
    List<Reservation> getUserReservations(Long userId);
    Reservation getReservationById(Long id);
}
