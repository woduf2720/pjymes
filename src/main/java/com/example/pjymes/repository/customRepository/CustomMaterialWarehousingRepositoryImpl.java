package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.QOrderMaster;
import com.example.pjymes.domain.QWarehousing;
import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomMaterialWarehousingRepositoryImpl implements CustomMaterialWarehousingRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Warehousing> findByKeyword(SearchDTO searchDTO) {
        QWarehousing qWarehousing = QWarehousing.warehousing;

        Predicate predicate = qWarehousing.orderMaster.customer.name.contains(searchDTO.getKeyword())
                .and(qWarehousing.createdAt.between(searchDTO.getStartDate().atStartOfDay(), searchDTO.getEndDate().atStartOfDay()));

        return jpaQueryFactory.selectFrom(qWarehousing)
                .where(predicate)
                .fetch();
    }
}
