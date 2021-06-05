package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.Sales;
import com.test.synsis.testsearchengine.model.SalesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, SalesKey> {

}
