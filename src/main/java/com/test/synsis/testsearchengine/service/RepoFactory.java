package com.test.synsis.testsearchengine.service;

import com.test.synsis.testsearchengine.dto.Category;
import com.test.synsis.testsearchengine.exception.BusinessException;
import com.test.synsis.testsearchengine.repository.ClientRepository;
import com.test.synsis.testsearchengine.repository.CountryRepository;
import com.test.synsis.testsearchengine.repository.ProductRepository;
import com.test.synsis.testsearchengine.repository.YearRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RepoFactory {

    private final ClientRepository clientRepository;
    private final CountryRepository countryRepository;
    private final ProductRepository productRepository;
    private final YearRepository yearRepository;

    private JpaRepository<?, Integer> getRepoByTableName(Category category) {
        switch (category) {
            case CLIENT:
                return clientRepository;
            case COUNTRY:
                return countryRepository;
            case PRODUCT:
                return productRepository;
            case YEAR:
                return yearRepository;
            default:
                throw new BusinessException("Invalid category of table");
        }
    }

    /**
     * Retrieves all records according to type
     *
     * @param category
     * @return
     */
    public List getAllRecordsByCategory(Category category) {
        return getRepoByTableName(category).findAll();
    }

}
