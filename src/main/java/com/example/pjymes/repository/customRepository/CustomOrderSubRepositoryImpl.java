package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderStatus;
import com.example.pjymes.domain.QOrderSub;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOrderSubRepositoryImpl implements CustomOrderSubRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public OrderStatus getQuantityMinusWarehousingQuantityByOrderNo(String orderNo) {
        QOrderSub qOrderSub = QOrderSub.orderSub;
        String statusName = jpaQueryFactory
                .select(
                        Expressions
                                .cases()
                                .when(qOrderSub.warehousingQuantity.sum().eq(0L)).then(OrderStatus.INITIAL.name())
                                .when(qOrderSub.warehousingQuantity.sum().lt(qOrderSub.quantity.sum())).then(OrderStatus.PARTIAL.name())
                                .otherwise(OrderStatus.FULLY.name())
                )
                .from(qOrderSub)
                .where(qOrderSub.orderMaster.orderNo.eq(orderNo))
                .fetchOne();
        return OrderStatus.valueOf(statusName);
    }
}
