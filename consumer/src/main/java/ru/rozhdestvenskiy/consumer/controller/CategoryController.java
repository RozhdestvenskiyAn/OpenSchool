package ru.rozhdestvenskiy.consumer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rozhdestvenskiy.consumer.integration.supplier.SupplierRestService;
import ru.rozhdestvenskiy.consumer.integration.supplier.dto.CategoryDto;
import ru.rozhdestvenskiy.consumer.integration.supplier.dto.ProductDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final SupplierRestService supplierRestService;

    @GetMapping
    public List<CategoryDto> getAll() {
        return supplierRestService.getAllCategories();
    }
}
