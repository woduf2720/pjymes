package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String code;
    private String name;
    private String specification;
    private Long categoryId;
    private String categoryName;
    private Long unitPrice;
    private Boolean active;
}
