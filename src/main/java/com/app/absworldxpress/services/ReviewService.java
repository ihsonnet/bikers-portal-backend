package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.AddReviewRequest;
import com.app.absworldxpress.model.ReviewModel;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    ResponseEntity<ApiResponse<ReviewModel>> addReview(String token, String productId, AddReviewRequest addReviewRequest);
}
