package dev.kunal.productcatalogservice.repository;

import dev.kunal.productcatalogservice.model.Product;
import dev.kunal.productcatalogservice.model.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Stack;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByIdAndState(Long id, State state);
}
