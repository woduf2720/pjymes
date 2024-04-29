package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.QCustomer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomCustomerRepositoryImpl implements CustomCustomerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Customer> findByKeyword(String keyword) {
        QCustomer qCustomer = QCustomer.customer;

        return jpaQueryFactory
                .selectFrom(qCustomer)
                .where(qCustomer.code.contains(keyword)
                        .or(qCustomer.name.contains(keyword)))
                .fetch();
    }
}
