package edu.library.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationResponse {
    private Long id;
    private LocalDate reservationDate;
    private Long userId;
    private Long bookId;
}
