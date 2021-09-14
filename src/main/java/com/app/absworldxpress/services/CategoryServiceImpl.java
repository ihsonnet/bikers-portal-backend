package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CategoryRequest;
import com.app.absworldxpress.model.CategoryModel;
import com.app.absworldxpress.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ApiMessageResponse> addCategory(CategoryRequest categoryRequest) {

        CategoryModel category = new CategoryModel();
        UUID id = UUID.randomUUID();
        String uuid = id.toString();

        category.setCatId(uuid);
        category.setCatName(categoryRequest.getCatName());
        category.setCatSlug(categoryRequest.getCatSlug());

        categoryRepository.save(category);
        return new ResponseEntity<>(new ApiMessageResponse(200,"Category Created Successfully"),HttpStatus.CREATED);
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
}
