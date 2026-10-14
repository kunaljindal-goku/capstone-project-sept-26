package dev.kunal.productcatalogservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Scanner;

@Getter
@Setter
@Entity
public class Product extends BaseEntity{

    private String name;

    @Column(precision = 19,scale = 3)
    private BigDecimal price;

    private String description;

    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
