package com.batchExample.configuration;

import com.batchExample.entity.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {

//    @Override
//    public @Nullable Product process(Product item) throws Exception {
//
//        try {
////
//            System.out.println(item.getProductId() + ":" + item.getDescription());
//
//
//             //Discounted Price
////            int i = Integer.parseInt(item.getPrice()) - (Integer.parseInt(item.getPrice()) * Integer.parseInt(item.getDiscount()) / 100);
//            double price = Double.parseDouble(item.getPrice());
//            double discount = Double.parseDouble(item.getDiscount());
//
//            double discountPrice = price - (price * discount / 100);
//
//            //System.out.println(discountPrice);
//            item.setDiscountedPrice(String.valueOf(discountPrice));
//
//        } catch (
//                NumberFormatException ex
//        ) {
//            ex.printStackTrace();
//        }
//
//        return item;
//    }

    @Override
    public Product process(Product product) {

        if(Integer.parseInt(product.getPrice()) < 1000){
            return null;   // skip this record
        }

        return product;
    }
}
