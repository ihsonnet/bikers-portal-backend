package com.app.absworldxpress.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryModel {
    @Id
    private String catId;
    private String catName;
    private String catSlug;
    private String catImage;

    private String createdBy;
    private Long creationTime;
    private String UpdatedBy;
    private Long updatedTime;
}
