package com.app.absworldxpress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
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

    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private Long creationTime;
    @JsonIgnore
    private String UpdatedBy;
    @JsonIgnore
    private Long updatedTime;
}
