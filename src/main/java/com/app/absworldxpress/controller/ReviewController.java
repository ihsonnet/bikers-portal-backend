package com.app.absworldxpress.controller;

import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.AddReviewRequest;
import com.app.absworldxpress.model.ReviewModel;
import com.app.absworldxpress.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review/")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewModel>> addReview(@RequestHeader(name = "Authorization") String token,
                                                              @RequestParam String productId,
                                                              @RequestBody AddReviewRequest addReviewRequest){
        return reviewService.addReview(token,productId,addReviewRequest);
    }
}
