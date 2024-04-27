package com.example.pjymes.repository;

import com.example.pjymes.domain.OrderSub;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSubRepository extends JpaRepository<OrderSub, Long>{

    @EntityGraph(attributePaths = {"item"})
    List<OrderSub> findAllByOrderMasterOrderNo(String orderNo);
}
