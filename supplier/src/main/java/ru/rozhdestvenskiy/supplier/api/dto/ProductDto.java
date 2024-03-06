package ru.rozhdestvenskiy.supplier.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.rozhdestvenskiy.supplier.api.validation.OnCreate;
import ru.rozhdestvenskiy.supplier.api.validation.OnUpdate;

@Data
public class ProductDto {
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Name must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    @Length(max = 255, message = "Description length must be smaller than 255 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String description;
    private Float price;
    private Long categoryId;
}
