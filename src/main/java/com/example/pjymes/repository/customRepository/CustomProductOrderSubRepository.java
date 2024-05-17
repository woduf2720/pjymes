package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderStatus;
import com.example.pjymes.domain.ProductOrderSub;

import java.util.List;

public interface CustomProductOrderSubRepository {

    OrderStatus getQuantityMinusWarehousingQuantityByOrderNo(String orderNo);

    List<ProductOrderSub> findUncompletedOrders();
}
