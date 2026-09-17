package com.Store.MiniStore_app.Repository;

import com.Store.MiniStore_app.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    boolean existsByCode(String productCode);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findAllByName(String name);
}
