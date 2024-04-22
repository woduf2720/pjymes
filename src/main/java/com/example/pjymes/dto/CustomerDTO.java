package com.example.pjymes.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private String customerCode;
    private String customerName;
    private String category;
    private String customerNumber;
    private String address;
    private String manager;
    private String managerPhone;
    private String managerEmail;
    private Boolean useStatus;
}
