package com.example.firstproject.dto;

import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Status.Status;
import com.example.firstproject.model.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @JsonProperty("order_id")
    private int order_id;

    @JsonProperty("order_dateTime")
    private LocalDateTime order_dateTime;

    @JsonProperty("total_price")
    private Float total_price;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("detail")
    private List<OrderDetailDTO> ordersDetail;

    @JsonProperty("user_id")
    private int user_id;

    @JsonProperty("address_id")
    private int address_id;
}
