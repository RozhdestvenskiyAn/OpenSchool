package ru.rozhdestvenskiy.consumer.integration.supplier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.rozhdestvenskiy.consumer.integration.supplier.dto.CategoryDto;
import ru.rozhdestvenskiy.consumer.integration.supplier.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierRestService {

    private final RestTemplate restTemplate;

    @Value("${integration.supplier.url}")
    private String url;

    public List<ProductDto> getAllProduct(String filter, Integer page, Integer size) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "product")
                .replaceQueryParam("filter", filter)
                .replaceQueryParam("page", page)
                .replaceQueryParam("size", size)
                .build();

        return ofNullable(restTemplate.getForObject(uri.encode().toUri(), ProductDto[].class))
                .map(List::of)
                .orElseGet(ArrayList::new);
    }
    public List<CategoryDto> getAllCategories() {
        return ofNullable(restTemplate.getForObject(url + "category", CategoryDto[].class))
                .map(List::of)
                .orElseGet(ArrayList::new);
    }

    public ProductDto createProduct(ProductDto productDto) {
        return restTemplate.postForObject(url + "product", productDto, ProductDto.class);
    }

    public void updateProduct(ProductDto productDto) {
        restTemplate.put(url + "product/{id}", productDto, Map.of("id", productDto.getId()));
    }
    public void deleteProduct(Long id) {
        restTemplate.delete(url + "product/{id}", Map.of("id", id));
    }
}
