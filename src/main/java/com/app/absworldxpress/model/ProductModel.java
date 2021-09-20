package com.app.absworldxpress.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductModel {
    @Id
    private String productId;
    private String productName;
    private String productSlug;
    private String productSKU;
    private String productDescription;
    private String productImage;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private CategoryModel categoryModel;

    private double buyingPrice;
    private double regularPrice;
    private double currentPrice;
    private double cashBack;

    private int numberOfOrder;
    private int productQuantity;
    private int stockAvailable;

    private String createdBy;
    private Long creationTime;
    private String updatedBy;
    private Long updatedTime;
}
