package com.example.pjymes.repository.customRepository;

public interface CustomOrderSubRepository {

    Long getQuantityMinusWarehousingQuantityByOrderNo(String orderNo);

}
