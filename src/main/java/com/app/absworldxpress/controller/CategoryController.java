package com.app.absworldxpress.controller;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CategoryRequest;
import com.app.absworldxpress.dto.response.CategoryImageResponse;
import com.app.absworldxpress.dto.response.CategoryResponse;
import com.app.absworldxpress.model.CategoryModel;
import com.app.absworldxpress.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiMessageResponse> addCategory(@RequestHeader(name = "Authorization") String token,@RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(token,categoryRequest);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryModel>>> getCategory(){
        return categoryService.getCategory();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiMessageResponse> deleteCategory(@RequestHeader(name = "Authorization") String token,@PathVariable String categoryId){
        return categoryService.deleteCategory(token,categoryId);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiMessageResponse> editCategory(@RequestHeader(name = "Authorization") String token, @PathVariable String categoryId, @RequestBody CategoryRequest categoryRequest){
        return categoryService.editCategory(token,categoryId, categoryRequest);
    }

    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryImageResponse>> uploadCategoryImage(@RequestHeader(name = "Authorization") String token,
                                                                                  @RequestParam(value = "image", required = true) MultipartFile aFile,
                                                                                  @PathVariable String categoryId) {
        return categoryService.uploadCategoryImage(token,aFile,categoryId);
    }

    @DeleteMapping("/image/{categoryId}")
    public ResponseEntity<ApiMessageResponse> deleteCategoryImage(@RequestHeader(name = "Authorization") String token,
                                                                  @PathVariable String categoryId) {
        return categoryService.deleteCategoryImage(token,categoryId);
    }
}
