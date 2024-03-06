package ru.rozhdestvenskiy.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rozhdestvenskiy.consumer.dto.ErrorMessage;
import ru.rozhdestvenskiy.consumer.integration.supplier.exception.SupplierRequestInvalidException;
import ru.rozhdestvenskiy.consumer.integration.supplier.exception.SupplierResourceNotFoundException;
import ru.rozhdestvenskiy.consumer.integration.supplier.exception.SupplierServerException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .flatMap(fieldError -> Stream.of(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.joining(". "));
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(message));
    }

    @ExceptionHandler(SupplierResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleSupplierResourceNotFoundException(SupplierResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(NOT_FOUND)
                .body(new ErrorMessage(e.getMessage()));
    }
    @ExceptionHandler(SupplierRequestInvalidException.class)
    public ResponseEntity<ErrorMessage> handleSupplierRequestInvalidException(SupplierRequestInvalidException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(SupplierServerException.class)
    public ResponseEntity<ErrorMessage> handleSupplierServerException(SupplierServerException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage("Что-то пошло не так!"));
    }
}
