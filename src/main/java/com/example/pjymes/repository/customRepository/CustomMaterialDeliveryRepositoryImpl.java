package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.Delivery;
import com.example.pjymes.domain.QDelivery;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomMaterialDeliveryRepositoryImpl implements CustomMaterialDeliveryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Delivery> findByKeyword(SearchDTO searchDTO) {
        QDelivery qDelivery = QDelivery.delivery;

        Predicate predicate = qDelivery.item.name.contains(searchDTO.getKeyword())
                .and(qDelivery.createdAt.between(searchDTO.getStartDate().atStartOfDay(), searchDTO.getEndDate().atStartOfDay()));

        return jpaQueryFactory.selectFrom(qDelivery)
                .where(predicate)
                .fetch();
    }
}
