package com.app.absworldxpress.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEditRequest {
    private String productName;
    private String productDescription;

    private String categoryId;

    private double buyingPrice;
    private double regularPrice;
    private double currentPrice;
    private double cashBack;
}
