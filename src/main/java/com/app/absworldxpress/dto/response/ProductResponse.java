package com.app.absworldxpress.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {
    private String productId;
    private String productName;
    private String productSlug;
    private String productSKU;
    private String productDescription;
    private String productImage;

    private String categoryName;

    private Integer regularPrice;
    private Integer currentPrice;
    private Integer cashBack;

    private Integer stockAvailable;
}
