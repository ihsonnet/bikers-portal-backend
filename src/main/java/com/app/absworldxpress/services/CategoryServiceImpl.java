package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CategoryRequest;
import com.app.absworldxpress.dto.response.CategoryImageResponse;
import com.app.absworldxpress.jwt.services.AuthService;
import com.app.absworldxpress.model.CategoryModel;
import com.app.absworldxpress.repository.CategoryRepository;
import com.app.absworldxpress.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<ApiMessageResponse> addCategory(String token, CategoryRequest categoryRequest) {

        if (authService.isAdmin(token)){
            CategoryModel category = new CategoryModel();
            UUID id = UUID.randomUUID();
            String uuid = id.toString();

            category.setCatId(uuid);
            category.setCatName(categoryRequest.getCatName());
            category.setCatSlug(categoryRequest.getCatSlug());

            categoryRepository.save(category);
            return new ResponseEntity<>(new ApiMessageResponse(201,"Category Created Successfully"),HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(new ApiMessageResponse(400,"You have no permission"),HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryModel>>> getCategory() {
        List<CategoryModel> allCategories = categoryRepository.findAll();
        if (!allCategories.isEmpty()){
            return new ResponseEntity<>(new ApiResponse<>(200,"Category Found",allCategories),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApiResponse<>(200,"Category Not Found",allCategories),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryImageResponse>> uploadCategoryImage(MultipartFile aFile, String categoryId) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryId);

        if (categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();

            MultipartFile[] multipartFiles = new MultipartFile[1];
            multipartFiles[0] = aFile;
            List<String> categoryImageLinks = new ArrayList<>();

            try {
                categoryImageLinks = UserUtils.uploadImage(multipartFiles);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
            }

            categoryModel.setCatImage(categoryImageLinks.get(0));
            categoryRepository.save(categoryModel);

            CategoryImageResponse categoryImageResponse = new CategoryImageResponse();
            categoryImageResponse.setCatImage(categoryImageLinks.get(0));
            return new ResponseEntity<>(new ApiResponse<>(200,"CategoryImage Uploaded Successfully",categoryImageResponse),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApiResponse<>(200,"Category Not Found",null),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ApiMessageResponse> deleteCategoryImage(String categoryId) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryId);

        if (categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();

            categoryModel.setCatImage(null);
            categoryRepository.save(categoryModel);

            return new ResponseEntity<>(new ApiMessageResponse(200,"CategoryImage Deleted Successfully"),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApiMessageResponse(200,"Category Not Found"),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ApiMessageResponse> deleteCategory(String token, String categoryId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiMessageResponse> editCategory(String token, String categoryId, CategoryRequest categoryRequest) {
        return null;
    }
}
