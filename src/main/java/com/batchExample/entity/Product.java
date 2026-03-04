package com.batchExample.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String title;
    private String description;

    private String price;

    private String discount;

    private  String discountedPrice;

//    public Product(String productId, String title, String description, String price, String discount) {
//        this.productId = productId;
//        this.title = title;
//        this.description = description;
//        this.price = price;
//        this.discount = discount;
//    }

}
