package com.test.synsis.testsearchengine.dto;

public enum Category {
    CLIENT,
    COUNTRY,
    PRODUCT,
    YEAR;

    public static Category findCategoryByName(String categoryName) {
        String categoryLowerCase = categoryName.toLowerCase();
        for (Category category : values()) {
            if (categoryLowerCase.equals(category.toString().toLowerCase())) {
                return category;
            }
        }
        return null;

    }
}
