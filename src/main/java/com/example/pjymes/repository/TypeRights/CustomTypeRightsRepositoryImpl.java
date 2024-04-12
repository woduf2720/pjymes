package com.example.pjymes.repository.TypeRights;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.domain.QCommonCode;
import com.example.pjymes.domain.QTypeRights;
import com.example.pjymes.domain.TypeRights;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomTypeRightsRepositoryImpl implements CustomTypeRightsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TypeRights> listBySubCode(String subCode) {
        QTypeRights qTypeRights = QTypeRights.typeRights;

        return jpaQueryFactory
                .select(qTypeRights)
                .from(qTypeRights)
                .where(qTypeRights.typeRightsId.typeId.eq(subCode))
                .fetch();
    }
}
