package com.app.absworldxpress.repository;

import com.app.absworldxpress.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,String> {
    boolean existsByProductId(String productId);
}
