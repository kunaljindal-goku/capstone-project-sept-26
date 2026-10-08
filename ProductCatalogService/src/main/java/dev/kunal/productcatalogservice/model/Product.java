package dev.kunal.productcatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseEntity{

    private String name;

    private Double price;  // BigDecimal -> very very precise

    private String description;

    private String imageUrl;

    private Category category;
}
