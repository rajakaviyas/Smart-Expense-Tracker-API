package com.smartexpensetracker.api;

import com.smartexpensetracker.expense.ExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ExpenseNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(java.util.Collections.singletonMap("message", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage(),
                        (first, second) -> first, LinkedHashMap::new));
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", "Validation failed");
        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }
}
