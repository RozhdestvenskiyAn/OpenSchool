package ru.rozhdestvenskiy.supplier.service;

import ru.rozhdestvenskiy.supplier.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category create(Category category);

    Category update(Long id, Category category);

    void deleteById(Long id);
}
