package com.example.pjymes.repository;

import com.example.pjymes.domain.QLotMaster;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomLotMasterRepositoryImpl implements CustomLotMasterRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public String getLotNo(String newLotNo) {
        QLotMaster qLotMaster = QLotMaster.lotMaster;

        Long getLotNo = (long) jpaQueryFactory.selectFrom(qLotMaster)
                .where(qLotMaster.lotNo.contains(newLotNo))
                .fetch().size() +1;        ;

        return String.format("%04d", getLotNo);
    }
}
