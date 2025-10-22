package com.e_commerce.dto.product.productDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilter {
    private Integer categoryId;

    private Integer productId;

    private String status;

    private String name;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;
}
