package ru.rozhdestvenskiy.consumer.integration.supplier.exception;

public class SupplierResourceNotFoundException extends RuntimeException{
    public SupplierResourceNotFoundException(String message) {
        super(message);
    }
}
