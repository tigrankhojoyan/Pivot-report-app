package com.test.synsis.testsearchengine.service.impl;

import com.test.synsis.testsearchengine.dto.Category;
import com.test.synsis.testsearchengine.dto.Measure;
import com.test.synsis.testsearchengine.exception.BusinessException;
import com.test.synsis.testsearchengine.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = ValidationServiceImpl.class)
class ValidationServiceImplTest {

    @Autowired
    ValidationServiceImpl validationService;

    @Test
    void validateReportGenerationInputOk() {
        validationService.validateReportGenerationInput(Category.CLIENT.toString().toLowerCase(), Category.COUNTRY.toString().toLowerCase(),
                Category.PRODUCT.toString().toLowerCase(), Measure.TOTAL_COAST.getMeasureValue());
    }

    @Test
    void validateReportGenerationInputNullValue() {
        assertThrows(BusinessException.class, () -> validationService.validateReportGenerationInput(null, Category.COUNTRY.toString().toLowerCase(),
                Category.PRODUCT.toString().toLowerCase(), Measure.TOTAL_COAST.getMeasureValue()));
    }

    @Test
    void validateReportGenerationInputInvalidInput() {
        assertThrows(BusinessException.class, () -> validationService.validateReportGenerationInput("aaa", Category.COUNTRY.toString().toLowerCase(),
                Category.PRODUCT.toString().toLowerCase(), Measure.TOTAL_COAST.getMeasureValue()));
    }

    @Test
    void validateReportGenerationInputDuplicateInput() {
        assertThrows(BusinessException.class, () -> validationService.validateReportGenerationInput(Category.COUNTRY.toString().toLowerCase(), Category.COUNTRY.toString().toLowerCase(),
                Category.PRODUCT.toString().toLowerCase(), Measure.TOTAL_COAST.getMeasureValue()));
    }
}