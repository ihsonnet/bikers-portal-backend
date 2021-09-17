package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CategoryRequest;
import com.app.absworldxpress.dto.response.CategoryImageResponse;
import com.app.absworldxpress.model.CategoryModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    ResponseEntity<ApiMessageResponse> addCategory(String token, CategoryRequest categoryRequest);

    ResponseEntity<ApiResponse<List<CategoryModel>>> getCategory();

    ResponseEntity<ApiResponse<CategoryImageResponse>> uploadCategoryImage(String token, MultipartFile aFile, String categoryId);

    ResponseEntity<ApiMessageResponse> deleteCategoryImage(String token, String categoryId);

    ResponseEntity<ApiMessageResponse> deleteCategory(String token, String categoryId);

    ResponseEntity<ApiMessageResponse> editCategory(String token, String categoryId, CategoryRequest categoryRequest);
}
