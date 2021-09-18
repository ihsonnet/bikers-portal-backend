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
    private String productImage;

    private String categoryId;

    private double buyingPrice;
    private double regularPrice;
    private double currentPrice;
    private double cashBack;

    private int stockAvailable;
}
