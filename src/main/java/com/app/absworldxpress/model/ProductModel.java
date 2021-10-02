package com.app.absworldxpress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<ReviewModel> reviewModelList;
    private Double productRating;
    @CollectionTable
    @ElementCollection
    private List<String> purchasedBy;

    private String createdBy;
    private Long creationTime;
    private String updatedBy;
    private Long updatedTime;
}
