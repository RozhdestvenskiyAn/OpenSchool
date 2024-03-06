package ru.rozhdestvenskiy.consumer.integration.supplier.exception;

public class SupplierRequestInvalidException extends RuntimeException{
    public SupplierRequestInvalidException(String message) {
        super(message);
    }
}
