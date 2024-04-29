package com.example.pjymes.repository;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.repository.customRepository.CustomOrderMasterRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>, CustomOrderMasterRepository {

}
