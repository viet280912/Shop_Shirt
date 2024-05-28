package com.example.firstproject.model.Order;

import com.example.firstproject.model.Status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrder {
    private Status status;
    private Float total_price;
    private LocalDateTime order_dateTime;
    private int address_id;
    private int user_id;
    private List<CreateOrderDetail> ordersDetail;
}
