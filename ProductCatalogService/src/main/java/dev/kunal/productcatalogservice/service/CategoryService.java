package dev.kunal.productcatalogservice.service;

import dev.kunal.productcatalogservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Double getAvgPriceForCategory(String category) {
        return categoryRepo.findAvgPriceForCategory(category);
    }
}
