package com.example.pjymes.dto;

import com.example.pjymes.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMasterDTO {

    private String orderNo;
    private String customerCode;
    private String customerName;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Long price;
    private int orderStatusValue;

    private List<OrderSubDTO> orderSubDTOList;
}
