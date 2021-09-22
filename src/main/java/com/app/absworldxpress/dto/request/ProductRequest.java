package com.app.absworldxpress.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String productDescription;

    private String categoryId;

    private Integer buyingPrice;
    private Integer regularPrice;
    private Integer currentPrice;
    private Integer cashBack;

    private Integer stockAvailable;
}
