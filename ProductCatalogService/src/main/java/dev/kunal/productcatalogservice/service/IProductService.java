package dev.kunal.productcatalogservice.service;

import dev.kunal.productcatalogservice.dto.ProductDto;
import dev.kunal.productcatalogservice.model.Product;

public interface IProductService {

    Product getProductById(Long id);

    Product createProduct(ProductDto productDto);

    boolean deleteProductById(Long id);
}
