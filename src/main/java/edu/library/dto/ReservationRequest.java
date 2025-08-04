package edu.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;
}
