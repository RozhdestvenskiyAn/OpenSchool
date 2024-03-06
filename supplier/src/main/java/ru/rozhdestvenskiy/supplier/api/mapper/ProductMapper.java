package ru.rozhdestvenskiy.supplier.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rozhdestvenskiy.supplier.api.dto.ProductDto;
import ru.rozhdestvenskiy.supplier.model.Product;
import ru.rozhdestvenskiy.supplier.service.CategoryService;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public abstract class ProductMapper {

    @Autowired
    protected CategoryService categoryService;

    @Mapping(expression = "java(categoryService.getById(productDto.getCategoryId()))", target = "category")
    public abstract Product mapToProduct(ProductDto productDto);
    public abstract List<Product> mapToProduct(List<ProductDto> productDtos);
    @Mapping(source = "category.id", target = "categoryId")
    public abstract ProductDto mapToProductDto(Product product);
    public abstract List<ProductDto> mapToProductDto(List<Product> products);
}
