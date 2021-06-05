package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String productName);
}
