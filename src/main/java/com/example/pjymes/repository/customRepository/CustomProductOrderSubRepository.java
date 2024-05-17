package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderStatus;

public interface CustomProductOrderSubRepository {

    OrderStatus getQuantityMinusWarehousingQuantityByOrderNo(String orderNo);

}
