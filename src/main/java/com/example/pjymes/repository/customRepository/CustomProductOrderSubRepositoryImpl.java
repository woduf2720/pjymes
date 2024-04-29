package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.QProductOrderSub;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomProductOrderSubRepositoryImpl implements CustomProductOrderSubRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long getQuantityMinusWarehousingQuantityByOrderNo(String orderNo) {
        QProductOrderSub qProductOrderSub = QProductOrderSub.productOrderSub;

        // 같은 orderNo를 가진 데이터들의 quantity 합계와 warehousingQuantity 합계의 차이를 계산
        return jpaQueryFactory
                .select(qProductOrderSub.quantity.sum().subtract(qProductOrderSub.deliveryQuantity.sum()))
                .from(qProductOrderSub)
                .where(qProductOrderSub.productOrderMaster.orderNo.eq(orderNo))
                .fetchOne();
    }
}
