package ru.rozhdestvenskiy.supplier.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.supplier.exception.ResourceNotFoundException;
import ru.rozhdestvenskiy.supplier.model.Category;
import ru.rozhdestvenskiy.supplier.repository.CategoryRepository;
import ru.rozhdestvenskiy.supplier.service.CategoryService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(Category category) {
        log.info("Save category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public List<Category> getAll() {
        log.info("Get all category");
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        log.info("Get category by id: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + id));
    }

    @Override
    @Transactional
    public Category update(Long id, Category category) {
        log.info("Update category: {}", category);
        Category existsCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + id));
        BeanUtils.copyProperties(category, existsCategory);
        return categoryRepository.save(existsCategory);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete category by id: {}", id);
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + id));
        categoryRepository.deleteById(id);
    }

}
