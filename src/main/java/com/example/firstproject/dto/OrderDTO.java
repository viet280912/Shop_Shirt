package com.example.firstproject.dto;

import com.example.firstproject.model.Status.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @JsonProperty("order_id")
    private int order_id;

    @JsonProperty("order_dateTime")
    private Date order_dateTime;

    @JsonProperty("total_price")
    private Float total_price;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("status")
    private List<OrderDetailDTO> ordersDetail;
}
