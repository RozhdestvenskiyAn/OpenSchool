package ru.rozhdestvenskiy.supplier.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    @NotNull(message = "Name must be not null.")
    private String name;
}
