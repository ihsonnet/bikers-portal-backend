package com.app.absworldxpress.repository;

import com.app.absworldxpress.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel,String> {
    boolean existsByProductId(String productId);
}
