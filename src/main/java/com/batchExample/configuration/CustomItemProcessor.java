package com.batchExample.configuration;

import com.batchExample.entity.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {

    @Override
    public @Nullable Product process(Product item) throws Exception {

        try {
//            put the percentage logic
            System.out.println(item.getProductId() + ":" + item.getDescription());
//            int discountPer = Integer.parseInt(item.getDiscount().trim());
//            double originalPrice = Double.parseDouble(item.getPrice().trim());
//            double discount = (discountPer / 100) * originalPrice;
//            double finalPrice = originalPrice - discount;
//            item.setDiscountedPrice(String.valueOf(finalPrice));
        } catch (
                NumberFormatException ex
        ) {
            ex.printStackTrace();
        }

        return item;
    }
}
