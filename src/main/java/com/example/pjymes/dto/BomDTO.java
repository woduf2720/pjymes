package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BomDTO {

    private Long level;
    private Long id;

    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private Long quantity;

    private String parentItemCode;
    private String parentItemName;

    @Builder.Default
    private List<BomDTO> children = new ArrayList<>();
}
