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

    private Integer buyingPrice;
    private Integer regularPrice;
    private Integer currentPrice;
    private Integer cashBack;

    private Integer numberOfOrder;
    private Integer productQuantity;
    private Integer stockAvailable;

    private String createdBy;
    private Long creationTime;
    private String updatedBy;
    private Long updatedTime;
}
