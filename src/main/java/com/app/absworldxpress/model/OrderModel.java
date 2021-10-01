package com.app.absworldxpress.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderModel {
    @Id
    private String orderId;
    private String orderSlug;
    private String customerName;
    private String customerId;
    private String customerPhoneNumber;
    private String deliveryAddress;

    private Integer orderAmount;
    private String orderStatus;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProductModel> productModelList;

    private Long creationTime;
    private String updatedBy;
    private Long updatedTime;
}
