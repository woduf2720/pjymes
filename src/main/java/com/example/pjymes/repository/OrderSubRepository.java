package com.example.pjymes.repository;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.OrderSub;
import com.example.pjymes.domain.OrderSubId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSubRepository extends JpaRepository<OrderSub, OrderSubId>{

}
