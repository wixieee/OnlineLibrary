package edu.library.utils;

import edu.library.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ErrorResponseMapper {

    public ErrorResponse from(HttpStatus status, String message, String path) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(status.value());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setPath(path);
        return response;
    }

    public ErrorResponse from(HttpStatus status, Exception ex, String path) {
        return from(status, ex.getMessage(), path);
    }
}
