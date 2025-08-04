package edu.library.controller;

import edu.library.dto.ReservationResponse;
import edu.library.dto.ReservationRequest;
import edu.library.model.Reservation;
import edu.library.service.ReservationService;
import edu.library.utils.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        Reservation reservation = reservationService.createReservation(reservationRequest.getUserId(), reservationRequest.getBookId());
        URI location = URI.create("/api/reservations/" + reservation.getId());
        return ResponseEntity.created(location).body(reservationMapper.toDTO(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationResponse>> getUserReservation(@PathVariable Long id) {
        List<ReservationResponse> reservations = reservationService.getUserReservations(id)
                .stream().map(reservationMapper::toDTO)
                .toList();

        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationMapper.toDTO(reservation));
    }
}
