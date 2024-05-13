package com.example.pjymes.domain;

import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.dto.ItemDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Setter
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private CommonCode category;
    private String registrationNumber;
    private String address;
    private String manager;
    private String managerPhone;
    private String managerEmail;
    private Boolean active;

    public void change(CustomerDTO customerDTO){
        this.name = customerDTO.getName();
        this.registrationNumber = customerDTO.getRegistrationNumber();
        this.address = customerDTO.getAddress();
        this.manager = customerDTO.getManager();
        this.managerPhone = customerDTO.getManagerPhone();
        this.managerEmail = customerDTO.getManagerEmail();
        this.active = customerDTO.getActive();
    }
}
