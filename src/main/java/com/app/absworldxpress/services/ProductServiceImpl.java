package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.BasicTableInfo;
import com.app.absworldxpress.dto.request.ProductEditRequest;
import com.app.absworldxpress.dto.request.ProductRequest;
import com.app.absworldxpress.dto.response.CategoryImageResponse;
import com.app.absworldxpress.dto.response.ProductListResponse;
import com.app.absworldxpress.jwt.services.AuthService;
import com.app.absworldxpress.model.CategoryModel;
import com.app.absworldxpress.model.ProductModel;
import com.app.absworldxpress.repository.CategoryRepository;
import com.app.absworldxpress.repository.ProductRepository;
import com.app.absworldxpress.util.ImageUtilService;
import com.app.absworldxpress.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UtilService utilService;

    @Autowired
    ImageUtilService imageUtilService;


    @Override
    public ResponseEntity<ApiResponse<ProductModel>> addProduct(String token, ProductRequest productRequest) {
        if (authService.isThisUser("ADMIN", token)){
            Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(productRequest.getCategoryId());
            if (categoryModelOptional.isPresent()){
                BasicTableInfo basicTableInfo = utilService.generateBasicTableInfo(productRequest.getProductName(),token);

                ProductModel productModel = ProductModel.builder()
                        .productId(basicTableInfo.getId())
                        .productName(productRequest.getProductName())
                        .productSlug(basicTableInfo.getSlug())
                        .productSKU(basicTableInfo.getSKU())
                        .productDescription(productRequest.getProductDescription())
                        .categoryModel(categoryModelOptional.get())
                        .productQuantity(productRequest.getStockAvailable())
                        .stockAvailable(productRequest.getStockAvailable())
                        .buyingPrice(productRequest.getBuyingPrice())
                        .regularPrice(productRequest.getRegularPrice())
                        .currentPrice(productRequest.getCurrentPrice())
                        .cashBack(productRequest.getCashBack())
                        .createdBy(basicTableInfo.getCreateBy())
                        .creationTime(basicTableInfo.getCreationTime())
                        .updatedBy(basicTableInfo.getCreateBy())
                        .updatedTime(basicTableInfo.getCreationTime())
                        .build();

                productRepository.save(productModel);

                return new ResponseEntity<>(new ApiResponse<>(201,"Product Added Successfully",productModel), HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>(new ApiResponse<>(400,"Category Not Found",null), HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(new ApiResponse<>(400,"Your have no Permission",null), HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<ApiResponse<ProductListResponse>> getProductList(String productName, String sortBy, Sort.Direction orderBy, int pageSize, int pageNo) {
        ProductModel productModel = ProductModel.builder()
                .productName(productName)
                .build();

        System.out.println("10");
        Pageable pageable;
        Sort sort = Sort.by(orderBy,sortBy);

        pageable = PageRequest.of(0, 20,sort);
        System.out.println("11");
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("productName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        System.out.println("12");
        Page<ProductModel> productModelPage = productRepository.findAll(Example.of(productModel,matcher), pageable);
        System.out.println("13");
        ProductListResponse productListResponse = new ProductListResponse(pageSize, pageNo, productModelPage.getContent().size(),
                productModelPage.isLast(), productModelPage.getTotalElements(), productModelPage.getTotalPages(),
                productModelPage.getContent());


        if (productModelPage.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>(200, "No Product Found", productListResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>(200, "Product Found", productListResponse), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<ProductModel>> editProduct(String token, ProductEditRequest productRequest, String productId) {
        if (authService.isThisUser("ADMIN", token)){
            Optional<ProductModel> productModelOptional = productRepository.findById(productId);
            if (productModelOptional.isPresent()){
                Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(productRequest.getCategoryId());
                ProductModel productModel = productModelOptional.get();
                productModel.setProductName(productRequest.getProductName());
                productModel.setProductDescription(productRequest.getProductDescription());
                productModel.setCategoryModel(categoryModelOptional.get());
                productModel.setBuyingPrice(productRequest.getBuyingPrice());
                productModel.setRegularPrice(productRequest.getRegularPrice());
                productModel.setCurrentPrice(productRequest.getCurrentPrice());
                productModel.setCashBack(productRequest.getCashBack());

                productRepository.save(productModel);
                return new ResponseEntity<>(new ApiResponse<>(200,"Product Updated Successfully",productModel),HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new ApiResponse<>(400,"Product Not Found",null),HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(new ApiResponse<>(400,"You have no permission",null),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiMessageResponse> deleteProduct(String token, String productId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiMessageResponse> deleteProductImage(String token, String productId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryImageResponse>> uploadProductImage(String token, MultipartFile aFile, String productId) {
        return null;
    }
}