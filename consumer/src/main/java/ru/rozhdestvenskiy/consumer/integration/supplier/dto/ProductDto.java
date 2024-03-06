package ru.rozhdestvenskiy.consumer.integration.supplier.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.rozhdestvenskiy.consumer.integration.supplier.validation.OnCreate;
import ru.rozhdestvenskiy.consumer.integration.supplier.validation.OnUpdate;

@Data
public class ProductDto {
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Name must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    private String description;
    private Float price;
    private Long categoryId;
}
