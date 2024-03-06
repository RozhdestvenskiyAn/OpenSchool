package ru.rozhdestvenskiy.consumer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rozhdestvenskiy.consumer.integration.supplier.SupplierRestService;
import ru.rozhdestvenskiy.consumer.integration.supplier.dto.ProductDto;
import ru.rozhdestvenskiy.consumer.integration.supplier.validation.OnCreate;
import ru.rozhdestvenskiy.consumer.integration.supplier.validation.OnUpdate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final SupplierRestService supplierRestService;

    @GetMapping
    public List<ProductDto> getAll(@RequestParam(required = false) String filter,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        log.info("Consumer get all");
        return supplierRestService.getAllProduct(filter, page, size);
    }

    @PostMapping
    public ProductDto create(@Validated(OnCreate.class) @RequestBody ProductDto productDto) {
        return supplierRestService.createProduct(productDto);
    }

    @PutMapping
    public void update(@Validated(OnUpdate.class) @RequestBody ProductDto productDto) {
        supplierRestService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        supplierRestService.deleteProduct(id);
    }
}
