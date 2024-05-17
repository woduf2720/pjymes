package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderStatus;
import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.domain.QProductOrderSub;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomProductOrderSubRepositoryImpl implements CustomProductOrderSubRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public OrderStatus getQuantityMinusWarehousingQuantityByOrderNo(String orderNo) {
        QProductOrderSub qProductOrderSub = QProductOrderSub.productOrderSub;

        String statusName = jpaQueryFactory
                .select(
                        Expressions
                                .cases()
                                .when(qProductOrderSub.deliveryQuantity.sum().eq(0L)).then(OrderStatus.INITIAL.name())
                                .when(qProductOrderSub.deliveryQuantity.sum().lt(qProductOrderSub.quantity.sum())).then(OrderStatus.PARTIAL.name())
                                .otherwise(OrderStatus.FULLY.name())
                )
                .from(qProductOrderSub)
                .where(qProductOrderSub.productOrderMaster.orderNo.eq(orderNo))
                .fetchOne();
        return OrderStatus.valueOf(statusName);
    }

    @Override
    public List<ProductOrderSub> findUncompletedOrders() {
        QProductOrderSub qProductOrderSub = QProductOrderSub.productOrderSub;
        return jpaQueryFactory
                .selectFrom(qProductOrderSub)
                .where(qProductOrderSub.quantity.subtract(qProductOrderSub.deliveryQuantity).ne(0L))
                .fetch();
    }
}
