package ru.rozhdestvenskiy.supplier.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.rozhdestvenskiy.supplier.api.dto.CategoryDto;
import ru.rozhdestvenskiy.supplier.model.Category;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {

    public Category mapToCategory(CategoryDto categoryDto);

    List<Category> mapToCategory(List<CategoryDto> categoryDtos);

    CategoryDto mapToCategoryDto(Category category);

    List<CategoryDto> mapToCategoryDto(List<Category> categories);
}
