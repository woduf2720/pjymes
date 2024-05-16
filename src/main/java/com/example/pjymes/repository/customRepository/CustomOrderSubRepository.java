package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderStatus;

public interface CustomOrderSubRepository {

    OrderStatus getQuantityMinusWarehousingQuantityByOrderNo(String orderNo);

}
