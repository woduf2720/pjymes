package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.QOrderSub;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOrderSubRepositoryImpl implements CustomOrderSubRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long getQuantityMinusWarehousingQuantityByOrderNo(String orderNo) {
        QOrderSub qOrderSub = QOrderSub.orderSub;

        // 같은 orderNo를 가진 데이터들의 quantity 합계와 warehousingQuantity 합계의 차이를 계산
        return jpaQueryFactory
                .select(qOrderSub.quantity.sum().subtract(qOrderSub.warehousingQuantity.sum()))
                .from(qOrderSub)
                .where(qOrderSub.orderMaster.orderNo.eq(orderNo))
                .fetchOne();
    }
}
