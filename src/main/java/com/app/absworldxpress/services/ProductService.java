package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.ProductEditRequest;
import com.app.absworldxpress.dto.request.ProductRequest;
import com.app.absworldxpress.dto.response.ProductImageResponse;
import com.app.absworldxpress.dto.response.ProductListResponse;
import com.app.absworldxpress.model.ProductModel;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ResponseEntity<ApiResponse<ProductModel>> addProduct(String token, ProductRequest productRequest);

    ResponseEntity<ApiResponse<ProductListResponse>> getProductList(String productName,String categoryId, String productId, String sortBy, Sort.Direction orderBy, int pageSize, int pageNo);

    ResponseEntity<ApiResponse<ProductModel>> editProduct(String token, ProductEditRequest productRequest, String productId);

    ResponseEntity<ApiMessageResponse> deleteProduct(String token, String productId);

    ResponseEntity<ApiMessageResponse> deleteProductImage(String token, String productId);

    ResponseEntity<ApiResponse<ProductImageResponse>> uploadProductImage(String token, MultipartFile aFile, String productId);

    ResponseEntity<ApiResponse<ProductListResponse>> getProductListForCpanel(String token, String productName, String categoryId, String productId, String sortBy, Sort.Direction orderBy, int pageSize, int pageNo);
}
