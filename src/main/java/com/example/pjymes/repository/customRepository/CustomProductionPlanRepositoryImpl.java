package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.domain.QProductionPlan;
import com.example.pjymes.dto.SearchDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomProductionPlanRepositoryImpl implements CustomProductionPlanRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductionPlan> findByKeyword(SearchDTO searchDTO) {
        QProductionPlan qProductionPlan = QProductionPlan.productionPlan;

        Predicate predicate = qProductionPlan.productOrderSub.item.name.contains(searchDTO.getKeyword())
                .and(qProductionPlan.orderDate.between(searchDTO.getStartDate(), searchDTO.getEndDate()));

        return jpaQueryFactory.selectFrom(qProductionPlan)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<ProductionPlan> findCompleteByKeyword(SearchDTO searchDTO) {
        QProductionPlan qProductionPlan = QProductionPlan.productionPlan;

        Predicate predicate = qProductionPlan.productOrderSub.item.name.contains(searchDTO.getKeyword())
                .and(qProductionPlan.orderDate.between(searchDTO.getStartDate(), searchDTO.getEndDate())
                        .and(qProductionPlan.endDate.isNotNull()));

        return jpaQueryFactory.selectFrom(qProductionPlan)
                .where(predicate)
                .fetch();
    }
}
