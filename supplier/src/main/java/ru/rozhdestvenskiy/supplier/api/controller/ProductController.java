package ru.rozhdestvenskiy.supplier.api.controller;

import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rozhdestvenskiy.supplier.api.dto.ProductDto;
import ru.rozhdestvenskiy.supplier.api.mapper.ProductMapper;
import ru.rozhdestvenskiy.supplier.api.validation.OnCreate;
import ru.rozhdestvenskiy.supplier.api.validation.OnUpdate;
import ru.rozhdestvenskiy.supplier.model.Product;
import ru.rozhdestvenskiy.supplier.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAll(@Filter Specification<Product> filter,
                                   Pageable pageable) {
        List<Product> products = productService.getAll(filter, pageable);
        return productMapper.mapToProductDto(products);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productMapper.mapToProductDto(product);
    }

    @PostMapping
    public ProductDto create(@Validated(OnCreate.class) @RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product createdProduct = productService.create(product);
        return productMapper.mapToProductDto(createdProduct);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @Validated(OnUpdate.class) @RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product updatedProduct = productService.update(id, product);
        productMapper.mapToProductDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
        ;
    }
}
