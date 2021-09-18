package com.app.absworldxpress.dto.response;

import com.app.absworldxpress.model.ProductModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductListResponse {
    int pageSize;
    int pageNo;
    int productCount;
    boolean isLastPage;
    Long totalProduct;
    int totalPages;

    List<ProductModel> productList;
}
