package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SalesRepositoryTest {

    @Autowired
    SalesRepository salesRepository;
    @Autowired
    YearRepository yearRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CountryRepository countryRepository;

    @Test
    void testSaveData() {
        Year year = Year.builder().name(Integer.valueOf(2005 ).toString()).sortId(1).id(2005).build();
        Client client = Client.builder().name("John ").build();
        Country country = Country.builder().name("USA").build();
        ProductType productType = ProductType.builder().name("Drink").build();
        yearRepository.save(year);
        clientRepository.save(client);
        ProductType persistedType = productTypeRepository.findByName(productType.getName());
        productType = persistedType == null ? productTypeRepository.save(productType) : persistedType;
        Product product = Product.builder().productType(productType).name("Cola ").build();
        product = productRepository.save(product);
        country = countryRepository.save(country);
        SalesKey salesKey = SalesKey.builder().clientId(client)
                .countryId(country).yearId(year).productId(product).build();
        Sales sales = Sales.builder().count(50 ).totalCost(2.5D).id(salesKey).build();
        salesRepository.save(sales);
        assertNotNull(salesRepository.getById(salesKey));
    }

}