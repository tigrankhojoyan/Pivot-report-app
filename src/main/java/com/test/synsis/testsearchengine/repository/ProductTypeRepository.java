package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    ProductType findByName(String name);
}
