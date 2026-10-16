package dev.kunal.productcatalogservice.repository;

import dev.kunal.productcatalogservice.model.Product;
import dev.kunal.productcatalogservice.model.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByIdAndState(Long id, State state);

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findByPriceBetweenAndState(BigDecimal minPrice,
                                             BigDecimal maxPrice,
                                             State state,
                                             Pageable pageable);

    @Query("SELECT p from Product p LEFT JOIN FETCH p.category c")
    List<Product> findAllProducts();
}
