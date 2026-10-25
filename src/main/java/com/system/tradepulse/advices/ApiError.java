package com.system.tradepulse.advices;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String errorMessage;
    private HttpStatus httpStatus;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String errorMessage, HttpStatus httpStatus) {
        this();
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
