package com.test.synsis.testsearchengine.service;

import com.test.synsis.testsearchengine.model.*;
import com.test.synsis.testsearchengine.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for generate test data when application starts
 */
@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class TestService {

    private final ClientRepository clientRepository;
    private final CountryRepository countryRepository;
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SalesRepository salesRepository;
    private final YearRepository yearRepository;

    public Sales saveTestData(int i, String productTypeName, String countryName) {
        Year year = Year.builder().name(Integer.valueOf(2005 + i % 3).toString()).sortId(1).id(2005 + i % 3).build();
        Client client = Client.builder().name("Poxos" + i).build();
        Country country = Country.builder().name(countryName).build();
        ProductType productType = ProductType.builder().name(productTypeName).build();
        yearRepository.save(year);
        clientRepository.save(client);
        ProductType persistedType = productTypeRepository.findByName(productType.getName());
        productType = persistedType == null ? productTypeRepository.save(productType) : persistedType;
        Product product = Product.builder().productType(productType).name("Cola " + i % 3).build();
        Product persistedProduct = productRepository.findByName(product.getName());
        product = persistedProduct != null ? persistedProduct : productRepository.save(product);
        Country persistedCountry = countryRepository.findByName(countryName);
        country = persistedCountry == null ? countryRepository.save(country) : persistedCountry;
        SalesKey salesKey = SalesKey.builder().clientId(client)
                .countryId(country).yearId(year).productId(product).build();
        return Sales.builder().count(50 + i * 2).totalCost(2.5D + i).id(salesKey).build();


    }

    public void saveSales(Sales sales) {
        salesRepository.save(sales);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void initTestData() {
        log.info("Init test data");
        String[] productTypes = {"Drink", "Vigitables", "Fruits", "Meat"};
        String[] countries = {"USA", "Canada", "Armenia", "France"};
        for (int i = 0; i < 16; i++) {
            Sales sales = saveTestData(i, productTypes[i % 4], countries[i % 4]);
            saveSales(sales);
        }
        log.info("Test data has been initialized successfully");
    }
}
