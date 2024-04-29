package com.example.pjymes.repository;

import com.example.pjymes.domain.OrderSub;
import com.example.pjymes.repository.customRepository.CustomOrderSubRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSubRepository extends JpaRepository<OrderSub, Long>, CustomOrderSubRepository {

    @EntityGraph(attributePaths = {"item"})
    List<OrderSub> findAllByOrderMasterOrderNo(String orderNo);
}
