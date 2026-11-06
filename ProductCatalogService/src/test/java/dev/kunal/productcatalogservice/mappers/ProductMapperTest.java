package dev.kunal.productcatalogservice.mappers;

import dev.kunal.productcatalogservice.dto.ProductDto;
import dev.kunal.productcatalogservice.model.Category;
import dev.kunal.productcatalogservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductMapperTest {

    Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
    }

    @Test
    public void testWhenValidEntity_thenReturnValidDto() {
        // Arrange
        ProductMapper mapper = new ProductMapper();
        Category category = new Category();
        category.setName("beauty");
        UUID id = UUID.randomUUID();
        product.setId(id);
        product.setName("Mascara");
        product.setPrice(new BigDecimal("9.99"));
        product.setCategory(category);

        ProductDto dto = mapper.toDto(product);

        assertEquals(dto.getId(),id);
        assertEquals(dto.getName(),"Mascara");
        assertEquals(dto.getPrice(),new BigDecimal("9.99"));
        assertEquals(dto.getCategory().getName(),"beauty");
    }

    @Test
    public void whenCategoryNotMapped_thenIsNull() {
        ProductMapper mapper = new ProductMapper();
        product.setName("Mascara");

        ProductDto dto = mapper.toDto(product);

        // If assert is not written, test always passes
        assertNull(dto.getCategory());
    }

    @Test
    public void whenOnlyPricePatched_thenOnlyPriceChanges() {
        ProductMapper mapper = new ProductMapper();
        product.setName("Mascara");
        product.setPrice(new BigDecimal("9.99"));
        product.setDescription("Volumizing");
        product.setImageUrl("http://img/1.png");

        ProductDto patch = new ProductDto();
        patch.setPrice(new BigDecimal("5.00"));

        Product updated = mapper.applyPatch(product, patch);

        assertEquals(new BigDecimal("5.00"), updated.getPrice());
        assertEquals("Mascara", updated.getName());
        assertEquals("Volumizing", updated.getDescription());
        assertEquals("http://img/1.png", updated.getImageUrl());
    }

    @Test
    public void whenPatchHasNoFields_thenProductUnchanged() {
        ProductMapper mapper = new ProductMapper();
        product.setName("Mascara");
        product.setPrice(new BigDecimal("9.99"));
        product.setDescription("Volumizing");
        product.setImageUrl("http://img/1.png");
        Category category = new Category();
        category.setName("beauty");
        product.setCategory(category);

        Product updated = mapper.applyPatch(product, new ProductDto());

        assertEquals("Mascara", updated.getName());
        assertEquals(new BigDecimal("9.99"), updated.getPrice());
        assertEquals("Volumizing", updated.getDescription());
        assertEquals("http://img/1.png", updated.getImageUrl());
        assertEquals("beauty", updated.getCategory().getName());
    }

    @Test
    public void whenAllScalarFieldsPatched_thenAllAreApplied() {
        ProductMapper mapper = new ProductMapper();
        product.setName("Mascara");
        product.setPrice(new BigDecimal("9.99"));
        product.setDescription("Volumizing");
        product.setImageUrl("http://img/1.png");

        ProductDto patch = new ProductDto();
        patch.setName("Lipstick");
        patch.setPrice(new BigDecimal("12.50"));
        patch.setDescription("Matte");
        patch.setImageUrl("http://img/2.png");

        Product updated = mapper.applyPatch(product, patch);

        assertEquals("Lipstick", updated.getName());
        assertEquals(new BigDecimal("12.50"), updated.getPrice());
        assertEquals("Matte", updated.getDescription());
        assertEquals("http://img/2.png", updated.getImageUrl());
    }

    @Test
    public void whenCategoryNotPatched_thenExistingCategoryIsPreserved() {
        ProductMapper mapper = new ProductMapper();
        product.setName("Mascara");
        Category category = new Category();
        category.setName("beauty");
        product.setCategory(category);

        ProductDto patch = new ProductDto();
        patch.setName("Lipstick");

        Product updated = mapper.applyPatch(product, patch);

        assertEquals("Lipstick", updated.getName());
        assertEquals("beauty", updated.getCategory().getName());
    }
}
