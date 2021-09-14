package com.app.absworldxpress.controller;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CategoryRequest;
import com.app.absworldxpress.dto.response.CategoryResponse;
import com.app.absworldxpress.model.CategoryModel;
import com.app.absworldxpress.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiMessageResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(categoryRequest);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryModel>>> getCategory(){
        return categoryService.getCategory();
    }


}
