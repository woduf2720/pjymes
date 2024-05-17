package com.example.pjymes.repository;

import com.example.pjymes.domain.ProductDelivery;
import com.example.pjymes.repository.customRepository.CustomProductDeliveryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, Long>, CustomProductDeliveryRepository {

}
