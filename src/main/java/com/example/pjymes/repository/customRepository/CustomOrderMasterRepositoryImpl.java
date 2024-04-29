package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.QOrderMaster;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomOrderMasterRepositoryImpl implements CustomOrderMasterRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderMaster> findByKeyword(SearchDTO searchDTO) {
        QOrderMaster qOrderMaster = QOrderMaster.orderMaster;

        Predicate predicate = qOrderMaster.customer.name.contains(searchDTO.getKeyword())
                .and(qOrderMaster.orderDate.between(searchDTO.getStartDate(), searchDTO.getEndDate()));

        return jpaQueryFactory.selectFrom(qOrderMaster)
                .where(predicate)
                .fetch();
    }

    @Override
    public String getOrderNo(String newOrderNo) {
        QOrderMaster qOrderMaster = QOrderMaster.orderMaster;
        Long getOrderNo = (long) jpaQueryFactory.selectFrom(qOrderMaster)
                .where(qOrderMaster.orderNo.contains(newOrderNo))
                .fetch().size() +1;        ;

        return String.format("%02d", getOrderNo);
    }
}
