package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.ProductDelivery;
import com.example.pjymes.domain.QProductDelivery;
import com.example.pjymes.domain.QWarehousing;
import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomProductDeliveryRepositoryImpl implements CustomProductDeliveryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductDelivery> findByKeyword(SearchDTO searchDTO) {
        QProductDelivery qProductDelivery = QProductDelivery.productDelivery;

        Predicate predicate = qProductDelivery.productOrderMaster.customer.name.contains(searchDTO.getKeyword())
                .and(qProductDelivery.createdAt.between(searchDTO.getStartDate().atStartOfDay(), searchDTO.getEndDate().atStartOfDay()));

        return jpaQueryFactory.selectFrom(qProductDelivery)
                .where(predicate)
                .fetch();
    }
}
