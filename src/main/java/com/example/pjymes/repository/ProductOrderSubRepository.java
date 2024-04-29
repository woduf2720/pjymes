package com.example.pjymes.repository;

import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.repository.customRepository.CustomProductOrderSubRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderSubRepository extends JpaRepository<ProductOrderSub, Long>, CustomProductOrderSubRepository {

    @EntityGraph(attributePaths = {"item"})
    List<ProductOrderSub> findAllByProductOrderMasterOrderNo(String orderNo);
}
