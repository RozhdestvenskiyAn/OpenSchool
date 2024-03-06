package ru.rozhdestvenskiy.supplier.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rozhdestvenskiy.supplier.api.dto.CategoryDto;
import ru.rozhdestvenskiy.supplier.api.mapper.CategoryMapper;
import ru.rozhdestvenskiy.supplier.model.Category;
import ru.rozhdestvenskiy.supplier.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryDto> getAll(){
        List<Category> categories = categoryService.getAll();
        return categoryMapper.mapToCategoryDto(categories);
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id){
        Category category = categoryService.getById(id);
        return categoryMapper.mapToCategoryDto(category);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        Category category = categoryMapper.mapToCategory(categoryDto);
        Category createdCategory = categoryService.create(category);
        return categoryMapper.mapToCategoryDto(createdCategory);
    }

    @PutMapping ("/{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.mapToCategory(categoryDto);
        Category updatedCategory = categoryService.update(id, category);
        return categoryMapper.mapToCategoryDto(updatedCategory);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);;
    }
}
