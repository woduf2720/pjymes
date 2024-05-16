package com.example.pjymes.repository;

import com.example.pjymes.domain.Delivery;
import com.example.pjymes.repository.customRepository.CustomMaterialDeliveryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDeliveryRepository extends JpaRepository<Delivery, Long>, CustomMaterialDeliveryRepository {

}
