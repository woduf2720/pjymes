package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String code;
    private String name;
    private Long categoryId;
    private String categoryName;
    private String registrationNumber;
    private String address;
    private String manager;
    private String managerPhone;
    private String managerEmail;
    private Boolean active;
}
