package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends JpaRepository<Year, Integer> {
}
