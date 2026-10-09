package dev.kunal.productcatalogservice.dto;

import dev.kunal.productcatalogservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String name;

    private Double price;

    private String description;

    private CategoryDto category;
}
