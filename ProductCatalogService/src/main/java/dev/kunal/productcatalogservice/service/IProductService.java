package dev.kunal.productcatalogservice.service;

import dev.kunal.productcatalogservice.dto.ProductDto;
import dev.kunal.productcatalogservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    Product getProductById(UUID id);

    Product createProduct(ProductDto productDto);

    Product updateProduct(UUID id, ProductDto productDto);

    boolean deleteProductById(UUID id);

    List<Product> getAllProducts();

    Page<Product> getProductsInPriceRange(BigDecimal minPrice,
                                          BigDecimal maxPrice,
                                          Pageable pageable);
}
