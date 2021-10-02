package com.app.absworldxpress.repository;

import com.app.absworldxpress.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewModel,String> {
}
