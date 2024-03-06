package ru.rozhdestvenskiy.supplier.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.rozhdestvenskiy.supplier.model.Product;

import java.util.List;

public interface ProductService {

    public Product create(Product product);
    public List<Product> getAll(Specification<Product> spec, Pageable pageable);
    public Product getById(Long id);
    public Product update(Long id, Product product);
    public void deleteById(Long id);
}
