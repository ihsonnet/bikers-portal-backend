package com.app.absworldxpress.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddReviewRequest {
    private Integer rating;
    private String comment;
}
