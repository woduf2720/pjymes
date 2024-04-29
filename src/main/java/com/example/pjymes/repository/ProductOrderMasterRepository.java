package com.example.pjymes.repository;

import com.example.pjymes.domain.ProductOrderMaster;
import com.example.pjymes.repository.customRepository.CustomProductOrderMasterRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderMasterRepository extends JpaRepository<ProductOrderMaster, String>, CustomProductOrderMasterRepository {

}
