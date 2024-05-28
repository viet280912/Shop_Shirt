package com.example.firstproject.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderDetail {
    private Float price;
    private int quantity;
    private int order_id;
    private int product_detail_id;
}
