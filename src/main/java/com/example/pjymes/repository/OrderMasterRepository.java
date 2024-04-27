package com.example.pjymes.repository;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.repository.materialManage.CustomOrderMasterRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>, CustomOrderMasterRepository {

}
