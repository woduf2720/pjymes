package com.example.pjymes.domain;

import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.dto.ItemDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    private String customerCode;
    private String customerName;
    private String category;
    private String customerNumber;
    private String address;
    private String manager;
    private String managerPhone;
    private String managerEmail;
    private Boolean useStatus;

    public void change(CustomerDTO customerDTO){
        this.customerName = customerDTO.getCustomerName();
        this.category = customerDTO.getCategory();
        this.customerNumber = customerDTO.getCustomerNumber();
        this.address = customerDTO.getAddress();
        this.manager = customerDTO.getManager();
        this.managerPhone = customerDTO.getManagerPhone();
        this.managerEmail = customerDTO.getManagerEmail();
        this.useStatus = customerDTO.getUseStatus();
    }
}
