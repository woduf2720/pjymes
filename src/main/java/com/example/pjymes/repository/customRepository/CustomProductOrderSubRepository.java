package com.example.pjymes.repository.customRepository;

public interface CustomProductOrderSubRepository {

    Long getQuantityMinusWarehousingQuantityByOrderNo(String orderNo);

}
