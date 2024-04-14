package com.example.pjymes.repository.MenuRights;

import com.example.pjymes.domain.MenuRights;
import com.example.pjymes.domain.QMenuRights;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomMenuRightsRepositoryImpl implements CustomMenuRightsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MenuRights> listBySubCode(String subCode) {
        QMenuRights qMenuRights = QMenuRights.menuRights;

        return jpaQueryFactory
                .select(qMenuRights)
                .from(qMenuRights)
                .where(qMenuRights.menuRightsId.typeId.eq(subCode))
                .fetch();
    }
}
