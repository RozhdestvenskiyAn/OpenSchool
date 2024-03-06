package ru.rozhdestvenskiy.consumer.integration.supplier.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import ru.rozhdestvenskiy.consumer.integration.supplier.exception.SupplierRequestInvalidException;
import ru.rozhdestvenskiy.consumer.integration.supplier.exception.SupplierResourceNotFoundException;
import ru.rozhdestvenskiy.consumer.integration.supplier.exception.SupplierServerException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

public class SupplierExceptionHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().isSameCodeAs(INTERNAL_SERVER_ERROR)) {
            throw new SupplierServerException("Ошибка на стороне сервера Supplier");
        }
        if (response.getStatusCode().isSameCodeAs(NOT_FOUND)) {
            // parse response.getBody()
            throw new SupplierResourceNotFoundException("Ресурс не найден на сервере Customer");
        }
        // parse response.getBody()
        if (response.getStatusCode().isSameCodeAs(UNPROCESSABLE_ENTITY)) {
            throw new SupplierRequestInvalidException("Не валидные данные");
        }
    }
}
