package ru.rozhdestvenskiy.supplier.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.supplier.exception.ResourceNotFoundException;
import ru.rozhdestvenskiy.supplier.model.Product;
import ru.rozhdestvenskiy.supplier.repository.ProductRepository;
import ru.rozhdestvenskiy.supplier.service.ProductService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(Product product) {
        log.info("Save product: {}", product);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public List<Product> getAll(Specification<Product> spec, Pageable pageable) {
        log.info("Get all product");
        Page<Product> page = productRepository.findAll(spec, pageable);
        return page.getContent();
//        return productRepository.findAll(spec, pageable);
    }

    @Override
    public Product getById(Long id) {
        log.info("Get product by id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + id));
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) {
        log.info("Update product: {}", product);
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + id));
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete product by id: {}", id);
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + id));
        productRepository.deleteById(id);
    }

}
