package dev.kunal.productcatalogservice.dto;

import dev.kunal.productcatalogservice.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private CategoryDto category;

    private String imageUrl;
}
