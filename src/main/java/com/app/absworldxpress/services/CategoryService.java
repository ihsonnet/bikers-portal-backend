package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CategoryRequest;
import com.app.absworldxpress.model.CategoryModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<ApiMessageResponse> addCategory(CategoryRequest categoryRequest);

    ResponseEntity<ApiResponse<List<CategoryModel>>> getCategory();
}
