package dev.kunal.productcatalogservice.service;

import dev.kunal.productcatalogservice.model.Product;

public interface IProductService {

    Product getProductById(Long id);
}
