package com.system.tradepulse.advices;

import com.system.tradepulse.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleResourceNotFoundException(ResourceNotFoundException exception){
        log.error(exception.getMessage(),exception);
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        ApiResponse<ApiError> apiResponse = new ApiResponse<>(apiError);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

}
