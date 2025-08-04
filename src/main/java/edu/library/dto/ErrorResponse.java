package edu.library.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int code;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}
