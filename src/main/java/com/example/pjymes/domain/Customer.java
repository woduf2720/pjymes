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
    private String code;
    private String name;
    private String category;
    private String registrationNumber;
    private String address;
    private String manager;
    private String managerPhone;
    private String managerEmail;
    private Boolean active;

    public void change(String name, String category, String registrationNumber, String address,
                       String manager, String managerPhone, String managerEmail, Boolean active){
        this.name = name;
        this.category = category;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.manager = manager;
        this.managerPhone = managerPhone;
        this.managerEmail = managerEmail;
        this.active = active;
    }
}
