package com.example.pjymes.dto;

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
public class ProductOrderMasterDTO {

    private String orderNo;
    private String customerCode;
    private String customerName;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Long price;
    private Boolean active;

    private List<ProductOrderSubDTO> productOrderSubDTOList;
}
