package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.ProductWarehousing;
import com.example.pjymes.domain.QProductWarehousing;
import com.example.pjymes.domain.QWarehousing;
import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomProductWarehousingRepositoryImpl implements CustomProductWarehousingRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductWarehousing> findByKeyword(SearchDTO searchDTO) {
        QProductWarehousing qProductWarehousing = QProductWarehousing.productWarehousing;

        Predicate predicate = qProductWarehousing.item.name.contains(searchDTO.getKeyword())
                .and(qProductWarehousing.createdAt.between(searchDTO.getStartDate().atStartOfDay(), searchDTO.getEndDate().atStartOfDay()));

        return jpaQueryFactory.selectFrom(qProductWarehousing)
                .where(predicate)
                .fetch();
    }
}
