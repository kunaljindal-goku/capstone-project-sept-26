package dev.kunal.productcatalogservice.service;

import dev.kunal.productcatalogservice.dto.ProductDto;
import dev.kunal.productcatalogservice.mappers.ProductMapper;
import dev.kunal.productcatalogservice.model.Category;
import dev.kunal.productcatalogservice.model.Product;
import dev.kunal.productcatalogservice.model.enums.State;
import dev.kunal.productcatalogservice.repository.CategoryRepository;
import dev.kunal.productcatalogservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@Slf4j
@RequiredArgsConstructor
public class StorageProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepo;
    private final ProductMapper productMapper;

//    public StorageProductService(ProductRepository productRepository,
//                                 CategoryRepository categoryRepo,
//                                 ProductMapper productMapper) {
//        this.productRepository = productRepository;
//        this.categoryRepo = categoryRepo;
//        this.productMapper = productMapper;
//    }


    @Override
    public Product getProductById(Long id) {
        log.info("Reading product from DB: "+id);
        return productRepository.findByIdAndState(id,State.ACTIVE).orElse(null);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setCategory(findOrCreateCategory(product.getCategory()));
        product.setCreatedAt(new Date());
        product.setLastUpdatedAt(new Date());
        product.setState(State.ACTIVE);
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product==null) return false;
        product.setState(State.INACTIVE);
        product.setLastUpdatedAt(new Date());
        productRepository.save(product);
        return true;
    }

    private Category findOrCreateCategory(Category category) {
        if (category == null) {
            return null;
        }

        Optional<Category> existing = categoryRepo.findByName(category.getName());
        if (existing.isPresent()) {
            return existing.get();
        }

        Date now = new Date();
        category.setId(null);
        category.setCreatedAt(now);
        category.setLastUpdatedAt(now);
        return category;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Page<Product> getProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetweenAndState(minPrice,
                maxPrice,
                State.ACTIVE,
                pageable);
    }
}
