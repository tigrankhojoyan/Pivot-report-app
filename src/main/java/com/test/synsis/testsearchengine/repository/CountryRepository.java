package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByName(String name);
}
