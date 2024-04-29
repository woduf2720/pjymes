package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.ProductOrderMaster;
import com.example.pjymes.domain.QOrderMaster;
import com.example.pjymes.domain.QProductOrderMaster;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomProductOrderMasterRepositoryImpl implements CustomProductOrderMasterRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductOrderMaster> findByKeyword(SearchDTO searchDTO) {
        QProductOrderMaster qProductOrderMaster = QProductOrderMaster.productOrderMaster;

        Predicate predicate = qProductOrderMaster.customer.name.contains(searchDTO.getKeyword())
                .and(qProductOrderMaster.orderDate.between(searchDTO.getStartDate(), searchDTO.getEndDate()));

        return jpaQueryFactory.selectFrom(qProductOrderMaster)
                .where(predicate)
                .fetch();
    }

    @Override
    public String getOrderNo(String newOrderNo) {
        QProductOrderMaster qProductOrderMaster = QProductOrderMaster.productOrderMaster;
        Long getOrderNo = (long) jpaQueryFactory.selectFrom(qProductOrderMaster)
                .where(qProductOrderMaster.orderNo.contains(newOrderNo))
                .fetch().size() +1;        ;

        return String.format("%02d", getOrderNo);
    }
}
