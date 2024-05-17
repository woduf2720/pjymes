package com.example.pjymes.repository;

import com.example.pjymes.domain.Delivery;
import com.example.pjymes.domain.ProductWarehousing;
import com.example.pjymes.repository.customRepository.CustomProductWarehousingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductWarehousingRepository extends JpaRepository<ProductWarehousing, Long>, CustomProductWarehousingRepository {

}
